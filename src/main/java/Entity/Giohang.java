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
@Table(name = "giohang")
@NamedQueries({
    @NamedQuery(name = "Giohang.findAll", query = "SELECT g FROM Giohang g"),
    @NamedQuery(name = "Giohang.findByTenTaiKhoan", query = "SELECT g FROM Giohang g WHERE g.giohangPK.tenTaiKhoan = :tenTaiKhoan"),
    @NamedQuery(name = "Giohang.findByIdSanPham", query = "SELECT g FROM Giohang g WHERE g.giohangPK.idSanPham = :idSanPham"),
    @NamedQuery(name = "Giohang.findBySoLuong", query = "SELECT g FROM Giohang g WHERE g.soLuong = :soLuong"),
    @NamedQuery(name = "Giohang.findByGia", query = "SELECT g FROM Giohang g WHERE g.gia = :gia")})
public class Giohang implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GiohangPK giohangPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soLuong")
    private int soLuong;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gia")
    private float gia;

    public Giohang() {
    }

    public Giohang(GiohangPK giohangPK) {
        this.giohangPK = giohangPK;
    }

    public Giohang(GiohangPK giohangPK, int soLuong, float gia) {
        this.giohangPK = giohangPK;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public Giohang(String tenTaiKhoan, int idSanPham) {
        this.giohangPK = new GiohangPK(tenTaiKhoan, idSanPham);
    }

    public GiohangPK getGiohangPK() {
        return giohangPK;
    }

    public void setGiohangPK(GiohangPK giohangPK) {
        this.giohangPK = giohangPK;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (giohangPK != null ? giohangPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Giohang)) {
            return false;
        }
        Giohang other = (Giohang) object;
        if ((this.giohangPK == null && other.giohangPK != null) || (this.giohangPK != null && !this.giohangPK.equals(other.giohangPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Giohang[ giohangPK=" + giohangPK + " ]";
    }
    
}
