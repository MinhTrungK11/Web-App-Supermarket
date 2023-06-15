/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "loaihang")
@NamedQueries({
    @NamedQuery(name = "Loaihang.findAll", query = "SELECT l FROM Loaihang l"),
    @NamedQuery(name = "Loaihang.findByIdLoaiHang", query = "SELECT l FROM Loaihang l WHERE l.idLoaiHang = :idLoaiHang"),
    @NamedQuery(name = "Loaihang.findByTenLoaiHang", query = "SELECT l FROM Loaihang l WHERE l.tenLoaiHang = :tenLoaiHang")})
public class Loaihang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "idLoaiHang")
    private String idLoaiHang;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tenLoaiHang")
    private String tenLoaiHang;
    @OneToMany(mappedBy = "idLoaiHoang")
    private Collection<Sanpham> sanphamCollection;
    @JoinColumn(name = "idNhomHang", referencedColumnName = "idNhomHang")
    @ManyToOne(optional = false)
    private Nhomhang idNhomHang;

    public Loaihang() {
    }

    public Loaihang(String idLoaiHang) {
        this.idLoaiHang = idLoaiHang;
    }

    public Loaihang(String idLoaiHang, String tenLoaiHang) {
        this.idLoaiHang = idLoaiHang;
        this.tenLoaiHang = tenLoaiHang;
    }

    public Loaihang(String idLoaiHang, Nhomhang idNhomHang, String tenLoaiHang) {
        this.idLoaiHang = idLoaiHang;
        this.tenLoaiHang = tenLoaiHang;
        this.idNhomHang = idNhomHang;
    }
    
    public String getIdLoaiHang() {
        return idLoaiHang;
    }

    public void setIdLoaiHang(String idLoaiHang) {
        this.idLoaiHang = idLoaiHang;
    }

    public String getTenLoaiHang() {
        return tenLoaiHang;
    }

    public void setTenLoaiHang(String tenLoaiHang) {
        this.tenLoaiHang = tenLoaiHang;
    }

    public Collection<Sanpham> getSanphamCollection() {
        return sanphamCollection;
    }

    public void setSanphamCollection(Collection<Sanpham> sanphamCollection) {
        this.sanphamCollection = sanphamCollection;
    }

    public Nhomhang getIdNhomHang() {
        return idNhomHang;
    }

    public void setIdNhomHang(Nhomhang idNhomHang) {
        this.idNhomHang = idNhomHang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoaiHang != null ? idLoaiHang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loaihang)) {
            return false;
        }
        Loaihang other = (Loaihang) object;
        if ((this.idLoaiHang == null && other.idLoaiHang != null) || (this.idLoaiHang != null && !this.idLoaiHang.equals(other.idLoaiHang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Loaihang[ idLoaiHang=" + idLoaiHang + " ]";
    }
    
}
