package br.com.grrecurso.core.managed;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.SelectItem;

import org.ocpsoft.shade.org.apache.commons.beanutils.BeanUtils;
import org.primefaces.component.column.Column;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.message.Message;
import org.primefaces.component.panel.Panel;

import br.com.grrecurso.core.persistence.BaseEntity;
import br.com.grrecurso.core.search.EvalExpression;
import br.com.grrecurso.core.search.FieldComboSelectOperations;
import br.com.grrecurso.core.search.FieldTextOperations;
import br.com.grrecurso.core.search.FieldTextPresentation;
import br.com.grrecurso.core.search.ResultGridBean;
import br.com.grrecurso.core.search.annotations.ConfiguracaoPesquisa;
import br.com.grrecurso.core.search.annotations.FieldComboSelectFilter;
import br.com.grrecurso.core.search.annotations.FieldTextFilter;
import br.com.grrecurso.core.search.annotations.ResultGrid;
import br.com.grrecurso.core.search.annotations.TituloPesquisa;
import br.com.grrecurso.core.service.GenericService;
import br.com.grrecurso.core.service.TradutorSQL;
import br.com.grrecurso.core.service.Traduzivel;

public class SearchEngine extends AbstractManagedBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3351273951665357806L;
	
	@EJB
	private GenericService genericService;
	
	private String tituloPesquisa="Pesquisa de ";
	private String tituloResultado = "Resultado da pesquisa";
	private String entidade = "Genérica";
	private String rowsPerPageTemplate = "10,25,50,100";
	private String varAttr = "row";
	
	private Panel mainPanel;
	protected Class<? extends BaseEntity> clazzEntity;
	private List<?> listaResultados = new ArrayList<>();
	private List<ResultGridBean> columnsLabelsGrid = new ArrayList<ResultGridBean>();
	private DataTable dataTable = null;	
	
	private Set<String> filtros = new HashSet<String>();
	private Traduzivel traduzivel = new TradutorSQL();
	
	protected void preInit(){
		
	}
	protected void posInit(){
		
	}
	
	public SearchEngine(){
		super();
	}
	
	@PostConstruct
	public void initBuild(){
		preInit();
		mainPanel = new Panel();
		try {
			entidade = clazzEntity.getSimpleName();
			mainPanel.setHeader(tituloPesquisa + entidade);
			
			if(clazzEntity.isAnnotationPresent(TituloPesquisa.class)) {
				TituloPesquisa pesquisa = (TituloPesquisa) clazzEntity.getAnnotation(TituloPesquisa.class);
				entidade = (pesquisa.entidade().isEmpty())?entidade:pesquisa.entidade();
				tituloPesquisa = (pesquisa.tituloPesquisa().isEmpty())?tituloPesquisa + entidade:pesquisa.tituloPesquisa();
				mainPanel.setHeader(tituloPesquisa);
				tituloResultado = (pesquisa.tituloResultado().isEmpty())?tituloResultado: pesquisa.tituloResultado();
			}
			
			if(clazzEntity.isAnnotationPresent(ConfiguracaoPesquisa.class)) {
				ConfiguracaoPesquisa config = (ConfiguracaoPesquisa) clazzEntity.getAnnotation(ConfiguracaoPesquisa.class);
				if(!config.rowsPerPageTemplate().isEmpty()) {
					rowsPerPageTemplate = config.rowsPerPageTemplate();
				}
				if(!config.varAttr().isEmpty()) {
					varAttr = config.varAttr();
				}
			}
			
		} catch (Exception e) {			
			HtmlOutputLabel error = new HtmlOutputLabel();
			error.setValue("Erro, contacte o administrador do sistema. " + e.getMessage());
			mainPanel.getChildren().add(error);
		}
		build();
		posInit();
	}
	
	/**
	 * Método utilizado para construir a tabela com o resultado da pesquisa.
	 */
	@SuppressWarnings("el-syntax")
	private void buildDataTable(){
		dataTable = new DataTable();
		UIOutput header = new UIOutput();
		header.setValue(tituloResultado);
		dataTable.getFacets().put("header", header);
		dataTable.setEmptyMessage("Nenhum resultado.");
		dataTable.setValue(listaResultados);
		dataTable.setVar(varAttr);
		dataTable.setPaginator(true);
		dataTable.setPaginatorPosition("bottom");
		dataTable.setPaginatorTemplate("{CurrentPageReport} {FirstPageLink} {PreviousPageLink} {PageLinks} {NextPageLink} {LastPageLink} {RowsPerPageDropdown}");
		dataTable.setRowsPerPageTemplate(rowsPerPageTemplate);
		
		Collections.sort(columnsLabelsGrid);
		
		for(ResultGridBean bean : columnsLabelsGrid){
			Column column = new Column();			
			UIOutput headerColumn = new UIOutput();
			headerColumn.setValue(bean.getLabel());
			column.setHeader(headerColumn);
			column.setStyle("text-align:" + bean.getAlinhamento());
			HtmlOutputText columnValue = new HtmlOutputText();
			columnValue.setValueExpression("value", createValueExpression("#{" + varAttr + "." + bean.getCampo() + "}" , String.class));
			
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
			buttonSearch.setStyleClass("btn btn-primary");
			String actionClassName = this.getClass().getSimpleName();
			actionClassName = actionClassName.substring(0,1).toLowerCase() + actionClassName.substring(1);
			buttonSearch.setActionExpression(createMethodExpression("#{" + actionClassName + ".pesquisar}"));
			UIOutput br = new UIOutput();
			br.setValue("<br/>");
			mainPanel.getChildren().add(br);
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
		Set<Map.Entry<?,?>> entries = (Set<Map.Entry<?,?>>)requestParameters.entrySet();
		Map<String, CriteriaBean> filter = new HashMap<String, CriteriaBean>();
		
		for(String campo : filtros){
			if(requestParameters.containsKey(EvalExpression.getIdCampo(campo))){
				String value = (String)requestParameters.get(EvalExpression.getIdCampo(campo));
				if(value != null && !value.isEmpty()){
					String operacao = (String)requestParameters.get(EvalExpression.getSufixoOperacao(campo));
					try {
						filter.put(campo, new CriteriaBean(campo, value, traduzivel.traduzir(operacao) ));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
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
		List<UIComponent> listaComponentes = new ArrayList<UIComponent>();
		
		getColumnsGrid().clear();
		for(Field f : getClazzEntity().getDeclaredFields()){
			if(f.isAnnotationPresent(ResultGrid.class)){
				ResultGrid resultGrid = f.getAnnotation(ResultGrid.class);
				String campo = (resultGrid.campo().isEmpty())?f.getName():resultGrid.campo();
				getColumnsGrid().add(new ResultGridBean(resultGrid.ordem(), campo, resultGrid.label(), resultGrid.align()));
			}
			if(f.isAnnotationPresent(FieldTextFilter.class)){
				FieldTextFilter filter = f.getAnnotation(FieldTextFilter.class);
				FieldTextPresentation apresentacao = filter.apresentacao();				
				String campo = (filter.campo().isEmpty()) ? f.getName() : filter.campo();
				this.filtros.add(campo);
				FieldTextOperations[] operations = filter.operacao();
				
				UIOutput initTag = new UIOutput();
				initTag.setValue("<div class=\"form-group\">");
				listaComponentes.add(initTag);
				
				//Inseri o label do campo do filtro de pesquisa
				HtmlOutputLabel label = new HtmlOutputLabel();
				label.setValue(filter.label());
				label.setFor(EvalExpression.getIdCampo(campo));
				listaComponentes.add(label);
					
					HtmlSelectOneMenu selectOneMenu = new HtmlSelectOneMenu();
					selectOneMenu.setId(EvalExpression.getSufixoOperacao(campo));
					selectOneMenu.setLabel("Operação");
					selectOneMenu.setRequired(filter.obrigatorio());
					//selectOneMenu.setConverter(new EnumConverter());				
					UISelectItems uiSelectItems = new UISelectItems();
					List<SelectItem> opcoes = new ArrayList<SelectItem>();
					for(FieldTextOperations operation : operations) {
						opcoes.add(new SelectItem(operation.toString(), operation.getDesc()));
					}
					uiSelectItems.setValue(opcoes);
					selectOneMenu.getChildren().add(uiSelectItems);
					
					listaComponentes.add(selectOneMenu);
					
					HtmlInputText inputText = new HtmlInputText();
					inputText.setId(EvalExpression.getIdCampo(campo));
					inputText.setStyleClass("form-control");
					inputText.setMaxlength(filter.maxLength());
					inputText.setSize(filter.size());
					inputText.setRequired(filter.obrigatorio());
					inputText.setRequiredMessage("O " + campo + " deve ser informado.");					
					listaComponentes.add(inputText);
					listaComponentes.add(getMessage(EvalExpression.getIdCampo(campo)));
					
				UIOutput endTag = new UIOutput();
				endTag.setValue("</div>");
				listaComponentes.add(endTag);
				
			} else if(f.isAnnotationPresent(FieldComboSelectFilter.class)){
				FieldComboSelectFilter filter = f.getAnnotation(FieldComboSelectFilter.class);								
				String campo = (filter.campo().isEmpty()) ? f.getName() : filter.campo();
				this.filtros.add(campo);			
				UIOutput initTag = new UIOutput();
				initTag.setValue("<div class=\"form-group\">");
				listaComponentes.add(initTag);
				
				//Inseri o label do campo do filtro de pesquisa
				HtmlOutputLabel label = new HtmlOutputLabel();
				label.setValue(filter.label());
				//Monta um Id para o componente input a partir do "campo"
				label.setFor(EvalExpression.getIdCampo(campo));
				listaComponentes.add(label);

				//Monta o menu com as operações disponíveis (Igual_a, Diferente_de, etc)
					HtmlSelectOneMenu selectOneMenuOperacao = new HtmlSelectOneMenu();
					HtmlSelectOneMenu selectOneMenuOpcoes = new HtmlSelectOneMenu();
					selectOneMenuOperacao.setId(EvalExpression.getSufixoOperacao(campo));
					selectOneMenuOperacao.setLabel("Operação");
					UISelectItems uiSelectItemsOperacao = new UISelectItems();
					UISelectItems uiSelectItemsOpcoes = new UISelectItems();
					List<SelectItem> opcoesOperacao = new ArrayList<SelectItem>();
					List<SelectItem> itemsOpcoes = new ArrayList<SelectItem>();
					
					FieldComboSelectOperations[] operations = new FieldComboSelectOperations[1];
					//Pode vir a ter mais opções no futuro - Implementação inicial (por isso o array)
					operations[0] = FieldComboSelectOperations.SELECT_IGUAL;
					
					for(FieldComboSelectOperations operation : operations) {
						opcoesOperacao.add(new SelectItem(operation.toString(), operation.getDesc()));
					}
					
					uiSelectItemsOperacao.setValue(opcoesOperacao);
					selectOneMenuOperacao.getChildren().add(uiSelectItemsOperacao);
					
					listaComponentes.add(selectOneMenuOperacao);
					
					
					if(filter.enumSource().isEnum()){
						//selectOneMenuOpcoes.setConverter(new EnumConverter());
						try {
							Enum[] values = getEnumValues(filter.enumSource());
							SelectItem selecione = new SelectItem();
							selecione.setLabel("Selecione");
							selecione.setNoSelectionOption(true);
							itemsOpcoes.add(selecione);
							for(Enum e : values){
								itemsOpcoes.add(new SelectItem(e.getClass().getName() + "#" + e, e.toString()));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(!filter.sourceValues().isEmpty()){
						try {
							String expressionSourceValue = filter.sourceValues();
							
							FacesContext context = FacesContext.getCurrentInstance();
						    ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
						    ELContext elContext = context.getELContext();
						    ValueExpression vex = expressionFactory.createValueExpression(elContext, expressionSourceValue, Object.class);
						    Collection<?> result = (Collection<?>)vex.getValue(elContext);
							
							if(result == null) return null;
							
							SelectItem selecione = new SelectItem();
							selecione.setLabel("Selecione");
							selecione.setNoSelectionOption(true);
							itemsOpcoes.add(selecione);
							for(Object bean : result){
								itemsOpcoes.add(new SelectItem(bean.getClass().getName() + "#" + BeanUtils.getProperty(bean, "id"), BeanUtils.getProperty(bean, "descricao")));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					uiSelectItemsOpcoes.setValue(itemsOpcoes);				
					selectOneMenuOpcoes.getChildren().add(uiSelectItemsOpcoes);
					selectOneMenuOpcoes.setStyleClass("form-control");
					selectOneMenuOpcoes.setId(EvalExpression.getIdCampo(campo));
					selectOneMenuOpcoes.setRequired(filter.obrigatorio());
					selectOneMenuOpcoes.setRequiredMessage("O " + campo + " deve ser informado.");
					
					listaComponentes.add(selectOneMenuOpcoes);					
					listaComponentes.add(getMessage(EvalExpression.getIdCampo(campo)));
					
				UIOutput endTag = new UIOutput();
				endTag.setValue("</div>");
				
				
				
				listaComponentes.add(endTag);
				
			}			
		}
		
		return listaComponentes;
	}
	
	protected Object getBean(String beanName){
		ELContext elContext = getELContext();
		Object bean = elContext.getELResolver().getValue(elContext, null, beanName);
		return bean;
	}
	
	private Message getMessage(String forCampo){
		Message message = new Message();
		message.setFor(forCampo);
		return message;
	}

	public Class<? extends BaseEntity> getClazzEntity() {
		return clazzEntity;
	}

	protected void setClazzEntity(Class<? extends BaseEntity> clazzEntity) {
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
	
	public Enum[] getEnumValues(Class<?> enumerator) throws Exception {		
		
		Method m = enumerator.getMethod("values");
		
		Enum[] result = (Enum[]) m.invoke(enumerator, null);
		
		return result;
	}
}
