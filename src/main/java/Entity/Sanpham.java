/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "sanpham")
@NamedQueries({
    @NamedQuery(name = "Sanpham.findAll", query = "SELECT s FROM Sanpham s"),
    @NamedQuery(name = "Sanpham.findByIdSanPham", query = "SELECT s FROM Sanpham s WHERE s.idSanPham = :idSanPham"),
    @NamedQuery(name = "Sanpham.findByTenSanPham", query = "SELECT s FROM Sanpham s WHERE s.tenSanPham = :tenSanPham"),
    @NamedQuery(name = "Sanpham.findBySoLuong", query = "SELECT s FROM Sanpham s WHERE s.soLuong = :soLuong"),
    @NamedQuery(name = "Sanpham.findByGiaNhap", query = "SELECT s FROM Sanpham s WHERE s.giaNhap = :giaNhap"),
    @NamedQuery(name = "Sanpham.findByGiaBan", query = "SELECT s FROM Sanpham s WHERE s.giaBan = :giaBan"),
    @NamedQuery(name = "Sanpham.findByHsd", query = "SELECT s FROM Sanpham s WHERE s.hsd = :hsd"),
    @NamedQuery(name = "Sanpham.findByMoTa", query = "SELECT s FROM Sanpham s WHERE s.moTa = :moTa"),
    @NamedQuery(name = "Sanpham.findByGiamGia", query = "SELECT s FROM Sanpham s WHERE s.giamGia = :giamGia")})
public class Sanpham implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSp")
    private Collection<PicSp> picSpCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSanPham")
    private Integer idSanPham;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "tenSanPham")
    private String tenSanPham;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soLuong")
    private int soLuong;
    @Basic(optional = false)
    @NotNull
    @Column(name = "giaNhap")
    private double giaNhap;
    @Basic(optional = false)
    @NotNull
    @Column(name = "giaBan")
    private double giaBan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hsd")
    @Temporal(TemporalType.DATE)
    private Date hsd;
    @Size(max = 1000)
    @Column(name = "moTa")
    private String moTa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "giamGia")
    private Double giamGia;
    @JoinColumn(name = "idLoaiHoang", referencedColumnName = "idLoaiHang")
    @ManyToOne
    private Loaihang idLoaiHoang;
    @JoinColumn(name = "idNhomHang", referencedColumnName = "idNhomHang")
    @ManyToOne
    private Nhomhang idNhomHang;

    public Sanpham() {
    }

    public Sanpham(Integer idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Sanpham(Integer idSanPham, String tenSanPham, int soLuong, double giaNhap, double giaBan, Date hsd) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.hsd = hsd;
    }

    public Sanpham(Integer idSanPham, Nhomhang idNhomHang, Loaihang idLoaiHoang, String tenSanPham, int soLuong, double giaNhap, double giaBan, Date hsd, String desc, Double disc) {
        this.idSanPham = idSanPham;
        this.idNhomHang = idNhomHang;
        this.idLoaiHoang = idLoaiHoang;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = desc;
        this.hsd = hsd;
        this.giamGia = disc;
    }
    
    public Integer getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Integer idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public Date getHsd() {
        return hsd;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Double giamGia) {
        this.giamGia = giamGia;
    }

    public Loaihang getIdLoaiHoang() {
        return idLoaiHoang;
    }

    public void setIdLoaiHoang(Loaihang idLoaiHoang) {
        this.idLoaiHoang = idLoaiHoang;
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
        hash += (idSanPham != null ? idSanPham.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sanpham)) {
            return false;
        }
        Sanpham other = (Sanpham) object;
        if ((this.idSanPham == null && other.idSanPham != null) || (this.idSanPham != null && !this.idSanPham.equals(other.idSanPham))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Sanpham[ idSanPham=" + idSanPham + " ]";
    }

    public Collection<PicSp> getPicSpCollection() {
        return picSpCollection;
    }

    public void setPicSpCollection(Collection<PicSp> picSpCollection) {
        this.picSpCollection = picSpCollection;
    }
    
}
