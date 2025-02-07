package org.anast.fowt.DTOs;

import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.property_mode;
import io.github.warnotte.waxlib3.core.TemplatePropertyMerger.Annotations.PROPERTY_interface;

public class Mooring

{
	
	double L = 1400; // Longueur du cable en mètre
	double D = 0.0809; // Diametre du cable en mètre
	double rho = 25368; // Densité du cable a l'air en k/m³
	double E = 114e9; // Young modulus en Pascal (N/mm²)
	double Cb = 1; // Coefficient de friction 
	double Zf = -10; // Z ammarage floteur (en dessous de l'eau).
	double Za = -130; // Z ammarage au sol (en dessous de l'eau). 
	/**
	 * @return the l
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 10)
	public double getL()
	{
		return L;
	}
	/**
	 * @param l the l to set
	 */
	public void setL(double l)
	{
		L = l;
	
	}
	/**
	 * @return the d
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 20)
	public double getD()
	{
		return D;
	}
	/**
	 * @param d the d to set
	 */
	public void setD(double d)
	{
		D = d;
	
	}
	/**
	 * @return the rho
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 30)
	public double getRho()
	{
		return rho;
	}
	/**
	 * @param rho the rho to set
	 */
	public void setRho(double rho)
	{
		this.rho = rho;
	
	}
	/**
	 * @return the e
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 40)
	public double getE()
	{
		return E;
	}
	/**
	 * @param e the e to set
	 */
	public void setE(double e)
	{
		E = e;
	
	}
	/**
	 * @return the cb
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 50)
	public double getCb()
	{
		return Cb;
	}
	/**
	 * @param cb the cb to set
	 */
	public void setCb(double cb)
	{
		Cb = cb;
	
	}
	/**
	 * @return the zf
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 60)
	public double getZf()
	{
		if (Zf > 0) return -Zf; 
		return Zf;
	}
	/**
	 * @param zf the zf to set
	 */
	public void setZf(double zf)
	{
		if (zf > 0) zf = -zf;
		Zf = zf;
	
	}
	/**
	 * @return the za
	 */
	@PROPERTY_interface(Operation = property_mode.PROPERTY_MERGEABLE, orderDisplay = 70)
	public double getZa()
	{
		if (Za > 0) return Za;
		return Za;
	}
	/**
	 * @param za the za to set
	 */
	public void setZa(double za)
	{
		if (Za > 0) za = -za; 
		Za = za;
	
	}
	
	
	
	//
}
