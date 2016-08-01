package br.com.grrecurso.core.managed;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.panel.Panel;

import br.com.grrecurso.core.persistence.BaseEntity;
import br.com.grrecurso.core.search.FieldTextOperations;
import br.com.grrecurso.core.search.FieldTextPresentation;
import br.com.grrecurso.core.search.ResultGridBean;
import br.com.grrecurso.core.search.annotations.FieldTextFilter;
import br.com.grrecurso.core.search.annotations.ResultGrid;
import br.com.grrecurso.core.service.GenericService;

@Named
@ViewScoped
public class SearchEngine extends AbstractManagedBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3351273951665357806L;
	
	@EJB
	private GenericService genericService;
	
	private Panel mainPanel;
	private Class<? extends BaseEntity> clazzEntity;
	private List<?> listaResultados = new ArrayList();
	private List<ResultGridBean> columnsLabelsGrid = new ArrayList<ResultGridBean>();
	private DataTable dataTable = null;
	
	@PostConstruct
	public void initBuild(){
		String className = getAttributeFromFlash(SEARCH_OBJECT);
		mainPanel = new Panel();
		
		mainPanel.setHeader("Pesquisa Genérica");
		try {
			setClazzEntity(Class.forName(className));
		} catch (ClassNotFoundException e) {			
			HtmlOutputLabel error = new HtmlOutputLabel();
			error.setValue("Erro, contacte o administrador do sistema. " + e.getMessage());
			mainPanel.getChildren().add(error);
		}
		build();
	}
	
	private void buildDataTable(){
		dataTable = new DataTable();
		UIOutput header = new UIOutput();
		header.setValue("Resultado da pesquisa");
		dataTable.getFacets().put("header", header);
		dataTable.setEmptyMessage("Nenhum resultado.");
		dataTable.setValue(listaResultados);
		dataTable.setVar("row");
		dataTable.setPaginator(true);
		dataTable.setPaginatorPosition("bottom");
		dataTable.setPaginatorTemplate("{CurrentPageReport} {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} {RowsPerPageDropdown}");
		dataTable.setRowsPerPageTemplate("10,25,50,100");
		
		Collections.sort(columnsLabelsGrid);
		
		for(ResultGridBean bean : columnsLabelsGrid){
			Column column = new Column();
			UIOutput headerColumn = new UIOutput();
			headerColumn.setValue(bean.getLabel());
			column.setHeader(headerColumn);
			HtmlOutputText columnValue = new HtmlOutputText();
			
			columnValue.setValueExpression("value", createValueExpression("#{row." + bean.getCampo() + "}" , String.class));
			
			column.getChildren().add(columnValue);
			dataTable.getChildren().add(column);
		}
	}
	
	private void build(){
		try {
			for(UIComponent comp : getFieldComponents()){
				mainPanel.getChildren().add(comp);				
			}
			HtmlCommandButton buttonSearch = new HtmlCommandButton();
			buttonSearch.setValue("Pesquisar");
			buttonSearch.setStyleClass("btn btn-default");
			buttonSearch.setActionExpression(createMethodExpression("#{searchEngine.pesquisar}"));
			
			mainPanel.getChildren().add(buttonSearch);
			buildDataTable();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public List<?> getListaResultados() {
		return listaResultados;
	}

	public void setListaResultados(List<?> listaResultados) {
		ArrayDataModel dataModel = new ArrayDataModel(listaResultados.toArray());
		getDataTable().setValue(dataModel);
		this.listaResultados = listaResultados;
	}

	public String pesquisar(){
		@SuppressWarnings({ "rawtypes"})
		
		Map requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Set<Map.Entry> entries = requestParameters.entrySet();
		Map filter = new HashMap();
		
		for(ResultGridBean bean : columnsLabelsGrid){
			if(requestParameters.containsKey(bean.getCampo())){
				String value = (String)requestParameters.get(bean.getCampo());
				if(value != null && !value.isEmpty()){
					filter.put(bean.getCampo(), value);
				}
			}
		}
		
		setListaResultados(genericService.list(filter, getClazzEntity()));
		
		if(!listaResultados.isEmpty()){
		}
		
		return "";
	}

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}

	private List<ResultGridBean> getColumnsGrid() {
		return columnsLabelsGrid;
	}

	private List<UIComponent> getFieldComponents() throws NoSuchFieldException, SecurityException{
		List<UIComponent> components = new ArrayList<UIComponent>();
		getColumnsGrid().clear();
		for(Field f : getClazzEntity().getDeclaredFields()){
			if(f.isAnnotationPresent(ResultGrid.class)){
				ResultGrid resultGrid = f.getAnnotation(ResultGrid.class);
				String campo = (resultGrid.campo().isEmpty())?f.getName():resultGrid.campo();
				getColumnsGrid().add(new ResultGridBean(resultGrid.ordem(), campo, resultGrid.label()));
			}
			if(f.isAnnotationPresent(FieldTextFilter.class)){
				FieldTextFilter filter = f.getAnnotation(FieldTextFilter.class);
				FieldTextPresentation apresentacao = filter.apresentacao();				
				String campo = (filter.campo().isEmpty()) ? f.getName() : filter.campo();
				FieldTextOperations[] operations = filter.operacao();
				
				UIOutput initTag = new UIOutput();
				initTag.setValue("<div class=\"form-group\">");
				components.add(initTag);
				
				HtmlOutputLabel label = new HtmlOutputLabel();
				label.setValue(filter.label());
				label.setFor(campo);
				components.add(label);
				
				HtmlInputText inputText = new HtmlInputText();
				inputText.setId(campo);
				inputText.setStyleClass("form-control");
				inputText.setMaxlength(filter.maxLength());
				inputText.setSize(filter.size());
				inputText.setRequired(filter.obrigatorio());
				components.add(inputText);
				
				UIOutput endTag = new UIOutput();
				endTag.setValue("</div>");
				components.add(endTag);
				
			}			
		}
		
		return components;
	}

	public Class getClazzEntity() {
		return clazzEntity;
	}

	private void setClazzEntity(Class clazzEntity) {
		this.clazzEntity = clazzEntity;
	}

	public Panel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(Panel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	private MethodExpression createMethodExpression(String action) {
		  final Class<?>[] paramTypes = new Class<?>[0];

		  MethodExpression methodExpression = getExpressionFactory()
		    .createMethodExpression(getELContext(),action, null, paramTypes);

		  return methodExpression;
		}

		private ValueExpression createValueExpression(String binding, Class clazz) {
		  final ValueExpression ve = getExpressionFactory().createValueExpression(getELContext(), binding, clazz);
		  return ve;
		}


		public static ELContext getELContext() {
		  return FacesContext.getCurrentInstance().getELContext();
		}

		public static ExpressionFactory getExpressionFactory() {
		  return getApplication().getExpressionFactory();
		}

		public static Application getApplication() {
		  return FacesContext.getCurrentInstance().getApplication();
		}
}
