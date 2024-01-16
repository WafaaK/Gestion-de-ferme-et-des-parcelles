package domaines;

import entities.Plantage;
import domaines.util.JsfUtil;
import domaines.util.JsfUtil.PersistAction;
import services.PlantageFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "plantageController")
@SessionScoped
public class PlantageController implements Serializable {

    @EJB
    private services.PlantageFacade ejbFacade;
    private List<Plantage> items = null;
    private Plantage selected;

    public PlantageController() {
    }

    public Plantage getSelected() {
        return selected;
    }

    public void setSelected(Plantage selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setPlantagePK(new entities.PlantagePK());
    }

    private PlantageFacade getFacade() {
        return ejbFacade;
    }

    public Plantage prepareCreate() {
        selected = new Plantage();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PlantageCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PlantageUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PlantageDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Plantage> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Plantage> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Plantage> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Plantage.class)
    public static class PlantageControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PlantageController controller = (PlantageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "plantageController");
            return controller.getFacade().find(getKey(value));
        }

        entities.PlantagePK getKey(String value) {
            entities.PlantagePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.PlantagePK();
            key.setDate(values[0]);
            key.setNombre(Integer.parseInt(values[1]));
            key.setParcelle(Integer.parseInt(values[2]));
            key.setPlante(Integer.parseInt(values[3]));
            return key;
        }

        String getStringKey(entities.PlantagePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getDate());
            sb.append(SEPARATOR);
            sb.append(value.getNombre());
            sb.append(SEPARATOR);
            sb.append(value.getParcelle());
            sb.append(SEPARATOR);
            sb.append(value.getPlante());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Plantage) {
                Plantage o = (Plantage) object;
                return getStringKey(o.getPlantagePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Plantage.class.getName()});
                return null;
            }
        }

    }

}
