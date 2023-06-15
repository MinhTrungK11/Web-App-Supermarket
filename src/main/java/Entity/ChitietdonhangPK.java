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

/**
 *
 * @author letua
 */
@Embeddable
public class ChitietdonhangPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "maDonHang")
    private int maDonHang;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSanPham")
    private int idSanPham;

    public ChitietdonhangPK() {
    }

    public ChitietdonhangPK(int maDonHang, int idSanPham) {
        this.maDonHang = maDonHang;
        this.idSanPham = idSanPham;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
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
        hash += (int) maDonHang;
        hash += (int) idSanPham;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChitietdonhangPK)) {
            return false;
        }
        ChitietdonhangPK other = (ChitietdonhangPK) object;
        if (this.maDonHang != other.maDonHang) {
            return false;
        }
        if (this.idSanPham != other.idSanPham) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ChitietdonhangPK[ maDonHang=" + maDonHang + ", idSanPham=" + idSanPham + " ]";
    }
    
}
