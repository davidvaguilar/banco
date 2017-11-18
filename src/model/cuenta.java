package model;

import modeldao.cuentadao;

public final class cuenta {
	private Integer cueId;
	private Integer cueSaldo;
	private String cueFecApertura;
	private String cueEstado;
	private int cueSobregiro;
	private cliente cli;
	
	public cuenta(String rut) {		
		this.cli=new cliente(); 
		cli.setPerRut(rut);
	}

	public cuenta(Integer cueId, 
			Integer cueSaldo, 
			String cueFecApertura, 
			String cueEstado, 
			int cueSobregiro,
			cliente cli) {
		this.cueId = cueId;
		this.cueSaldo = cueSaldo;
		this.cueFecApertura = cueFecApertura;
		this.cueEstado = cueEstado;
		this.cueSobregiro = cueSobregiro;
		this.cli = cli;
	}

	public Integer getCueId() {		return cueId;	}
	public void setCueId(Integer cueId) {		this.cueId = cueId;	}
	public Integer getCueSaldo() {		return cueSaldo;	}
	public void setCueSaldo(Integer cueSaldo) {		this.cueSaldo = cueSaldo;	}
	public String getCueFecApertura() {		return cueFecApertura;	}
	public void setCueFecApertura(String cueFecApertura) {		this.cueFecApertura = cueFecApertura;	}
	public String getCueEstado() {		return cueEstado;	}
	public void setCueEstado(String cueEstado) {		this.cueEstado = cueEstado;	}
	public int getCueSobregiro() {		return cueSobregiro;	}
	public void setCueSobregiro(int cueSobregiro) {		this.cueSobregiro = cueSobregiro;	}
	public cliente getCli() {		return cli;	}
	public void setCli(cliente cli) {		this.cli = cli;	}

	@Override
	public String toString() {
		return "cuenta [cueId=" + cueId 
				+ ", cueSaldo=" + cueSaldo 
				+ ", cueFecApertura=" + cueFecApertura
				+ ", cueEstado=" + cueEstado 
				+ ", cueSobregiro=" + cueSobregiro 
				+ ", cli=" + cli + "]";
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cueId == null) ? 0 : cueId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		cuenta other = (cuenta) obj;
		if (cueId == null) {
			if (other.cueId != null)
				return false;
		} else if (!cueId.equals(other.cueId))
			return false;
		return true;
	}

	public boolean depositar(Integer monto) {
		cuentadao cueDao=new cuentadao();
		cueDao.buscar(this);
		this.setCueSaldo(this.getCueSaldo()+monto);//OPERACION DEPOSITO
		return cueDao.actualizarSaldo(this);	//ACTUALIZO EL SALDO 
	}
	
	
}
