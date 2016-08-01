package br.com.grrecurso.core.search;

import java.io.Serializable;

public class ResultGridBean implements Serializable, Comparable<ResultGridBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 760063541555236572L;
	
	private int ordem;
	private String campo;
	private String label;
	
	public ResultGridBean(String campo, String label){
		setOrdem(ordem);
		setCampo(campo);
	}
	
	public ResultGridBean(int ordem, String campo, String label){
		setOrdem(ordem);
		setCampo(campo);
		setLabel(label);
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ordem;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultGridBean other = (ResultGridBean) obj;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (ordem != other.ordem)
			return false;
		return true;
	}

	@Override
	public int compareTo(ResultGridBean bean) {
		return Integer.valueOf(this.ordem).compareTo(Integer.valueOf(bean.getOrdem()));
	}
}
