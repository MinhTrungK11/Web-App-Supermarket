/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author letua
 */
@Embeddable
public class GiohangPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tenTaiKhoan")
    private String tenTaiKhoan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "idSanPham")
    private int idSanPham;

    public GiohangPK() {
    }

    public GiohangPK(String tenTaiKhoan, int idSanPham) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.idSanPham = idSanPham;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tenTaiKhoan != null ? tenTaiKhoan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GiohangPK)) {
            return false;
        }
        GiohangPK other = (GiohangPK) object;
        if ((this.tenTaiKhoan == null && other.tenTaiKhoan != null) || (this.tenTaiKhoan != null && !this.tenTaiKhoan.equals(other.tenTaiKhoan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.GiohangPK[ tenTaiKhoan=" + tenTaiKhoan + ", idSanPham=" + idSanPham + " ]";
    }
    
}
