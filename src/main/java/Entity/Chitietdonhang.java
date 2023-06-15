/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author letua
 */
@Entity
@Table(name = "chitietdonhang")
@NamedQueries({
    @NamedQuery(name = "Chitietdonhang.findAll", query = "SELECT c FROM Chitietdonhang c"),
    @NamedQuery(name = "Chitietdonhang.findByMaDonHang", query = "SELECT c FROM Chitietdonhang c WHERE c.chitietdonhangPK.maDonHang = :maDonHang"),
    @NamedQuery(name = "Chitietdonhang.findByIdSanPham", query = "SELECT c FROM Chitietdonhang c WHERE c.chitietdonhangPK.idSanPham = :idSanPham"),
    @NamedQuery(name = "Chitietdonhang.findBySoLuong", query = "SELECT c FROM Chitietdonhang c WHERE c.soLuong = :soLuong"),
    @NamedQuery(name = "Chitietdonhang.findByThanhTien", query = "SELECT c FROM Chitietdonhang c WHERE c.thanhTien = :thanhTien")})
public class Chitietdonhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChitietdonhangPK chitietdonhangPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soLuong")
    private int soLuong;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thanhTien")
    private float thanhTien;

    public Chitietdonhang() {
    }

    public Chitietdonhang(ChitietdonhangPK chitietdonhangPK) {
        this.chitietdonhangPK = chitietdonhangPK;
    }

    public Chitietdonhang(ChitietdonhangPK chitietdonhangPK, int soLuong, float thanhTien) {
        this.chitietdonhangPK = chitietdonhangPK;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public Chitietdonhang(int maDonHang, int idSanPham) {
        this.chitietdonhangPK = new ChitietdonhangPK(maDonHang, idSanPham);
    }

    public ChitietdonhangPK getChitietdonhangPK() {
        return chitietdonhangPK;
    }

    public void setChitietdonhangPK(ChitietdonhangPK chitietdonhangPK) {
        this.chitietdonhangPK = chitietdonhangPK;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chitietdonhangPK != null ? chitietdonhangPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chitietdonhang)) {
            return false;
        }
        Chitietdonhang other = (Chitietdonhang) object;
        if ((this.chitietdonhangPK == null && other.chitietdonhangPK != null) || (this.chitietdonhangPK != null && !this.chitietdonhangPK.equals(other.chitietdonhangPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Chitietdonhang[ chitietdonhangPK=" + chitietdonhangPK + " ]";
    }
    
}
