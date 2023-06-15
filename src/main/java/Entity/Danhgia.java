/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author letua
 */
@Entity
@Table(name = "danhgia")
@NamedQueries({
    @NamedQuery(name = "Danhgia.findAll", query = "SELECT d FROM Danhgia d"),
    @NamedQuery(name = "Danhgia.findByIdDanhGia", query = "SELECT d FROM Danhgia d WHERE d.idDanhGia = :idDanhGia"),
    @NamedQuery(name = "Danhgia.findByThoiGian", query = "SELECT d FROM Danhgia d WHERE d.thoiGian = :thoiGian"),
    @NamedQuery(name = "Danhgia.findByNoiDung", query = "SELECT d FROM Danhgia d WHERE d.noiDung = :noiDung")})
public class Danhgia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDanhGia")
    private Integer idDanhGia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGian")
    @Temporal(TemporalType.DATE)
    private Date thoiGian;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "noiDung")
    private String noiDung;
    @JoinColumn(name = "taiKhoan", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Account taiKhoan;
    @JoinColumn(name = "idSanPham", referencedColumnName = "idSanPham")
    @ManyToOne(optional = false)
    private Sanpham idSanPham;

    public Danhgia() {
    }

    public Danhgia(Integer idDanhGia) {
        this.idDanhGia = idDanhGia;
    }

    public Danhgia(Integer idDanhGia, Date thoiGian, String noiDung) {
        this.idDanhGia = idDanhGia;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
    }
    
    public Danhgia(Account taiKhoan, Sanpham idSanPham, Date thoiGian, String noiDung) {
        this.taiKhoan = taiKhoan;
        this.idSanPham = idSanPham;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
    }
    
    
    public Integer getIdDanhGia() {
        return idDanhGia;
    }

    public void setIdDanhGia(Integer idDanhGia) {
        this.idDanhGia = idDanhGia;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Account getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(Account taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Sanpham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Sanpham idSanPham) {
        this.idSanPham = idSanPham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDanhGia != null ? idDanhGia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Danhgia)) {
            return false;
        }
        Danhgia other = (Danhgia) object;
        if ((this.idDanhGia == null && other.idDanhGia != null) || (this.idDanhGia != null && !this.idDanhGia.equals(other.idDanhGia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Danhgia[ idDanhGia=" + idDanhGia + " ]";
    }
    
}
