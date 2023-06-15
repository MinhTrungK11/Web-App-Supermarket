/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author letua
 */
@Entity
@Table(name = "nhomhang")
@NamedQueries({
    @NamedQuery(name = "Nhomhang.findAll", query = "SELECT n FROM Nhomhang n"),
    @NamedQuery(name = "Nhomhang.findByIdNhomHang", query = "SELECT n FROM Nhomhang n WHERE n.idNhomHang = :idNhomHang"),
    @NamedQuery(name = "Nhomhang.findByTenNhomHang", query = "SELECT n FROM Nhomhang n WHERE n.tenNhomHang = :tenNhomHang")})
public class Nhomhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "idNhomHang")
    private String idNhomHang;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tenNhomHang")
    private String tenNhomHang;
    @OneToMany(mappedBy = "idNhomHang")
    private Collection<Sanpham> sanphamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhomHang")
    private Collection<Loaihang> loaihangCollection;

    public Nhomhang() {
    }

    public Nhomhang(String idNhomHang) {
        this.idNhomHang = idNhomHang;
    }

    public Nhomhang(String idNhomHang, String tenNhomHang) {
        this.idNhomHang = idNhomHang;
        this.tenNhomHang = tenNhomHang;
    }

    public String getIdNhomHang() {
        return idNhomHang;
    }

    public void setIdNhomHang(String idNhomHang) {
        this.idNhomHang = idNhomHang;
    }

    public String getTenNhomHang() {
        return tenNhomHang;
    }

    public void setTenNhomHang(String tenNhomHang) {
        this.tenNhomHang = tenNhomHang;
    }

    public Collection<Sanpham> getSanphamCollection() {
        return sanphamCollection;
    }

    public void setSanphamCollection(Collection<Sanpham> sanphamCollection) {
        this.sanphamCollection = sanphamCollection;
    }

    public Collection<Loaihang> getLoaihangCollection() {
        return loaihangCollection;
    }

    public void setLoaihangCollection(Collection<Loaihang> loaihangCollection) {
        this.loaihangCollection = loaihangCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNhomHang != null ? idNhomHang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nhomhang)) {
            return false;
        }
        Nhomhang other = (Nhomhang) object;
        if ((this.idNhomHang == null && other.idNhomHang != null) || (this.idNhomHang != null && !this.idNhomHang.equals(other.idNhomHang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Nhomhang[ idNhomHang=" + idNhomHang + " ]";
    }
    
}
