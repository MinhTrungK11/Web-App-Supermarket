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
import javax.persistence.Id;
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
@Table(name = "donhang")
@NamedQueries({
    @NamedQuery(name = "Donhang.findAll", query = "SELECT d FROM Donhang d"),
    @NamedQuery(name = "Donhang.findByMaDonHang", query = "SELECT d FROM Donhang d WHERE d.maDonHang = :maDonHang"),
    @NamedQuery(name = "Donhang.findByTaiKhoan", query = "SELECT d FROM Donhang d WHERE d.taiKhoan = :taiKhoan"),
    @NamedQuery(name = "Donhang.findByHoVaTen", query = "SELECT d FROM Donhang d WHERE d.hoVaTen = :hoVaTen"),
    @NamedQuery(name = "Donhang.findByDiaChi", query = "SELECT d FROM Donhang d WHERE d.diaChi = :diaChi"),
    @NamedQuery(name = "Donhang.findBySdt", query = "SELECT d FROM Donhang d WHERE d.sdt = :sdt"),
    @NamedQuery(name = "Donhang.findByEmail", query = "SELECT d FROM Donhang d WHERE d.email = :email"),
    @NamedQuery(name = "Donhang.findByTongThanhToan", query = "SELECT d FROM Donhang d WHERE d.tongThanhToan = :tongThanhToan"),
    @NamedQuery(name = "Donhang.findByThanhToan", query = "SELECT d FROM Donhang d WHERE d.thanhToan = :thanhToan"),
    @NamedQuery(name = "Donhang.findByChuThich", query = "SELECT d FROM Donhang d WHERE d.chuThich = :chuThich"),
    @NamedQuery(name = "Donhang.findByTinhTrang", query = "SELECT d FROM Donhang d WHERE d.tinhTrang = :tinhTrang"),
    @NamedQuery(name = "Donhang.findByNgayDat", query = "SELECT d FROM Donhang d WHERE d.ngayDat = :ngayDat")})
public class Donhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "maDonHang")
    private Integer maDonHang;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "taiKhoan")
    private String taiKhoan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "hoVaTen")
    private String hoVaTen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "diaChi")
    private String diaChi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "sdt")
    private String sdt;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tongThanhToan")
    private float tongThanhToan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "thanhToan")
    private String thanhToan;
    @Size(max = 100)
    @Column(name = "chuThich")
    private String chuThich;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tinhTrang")
    private String tinhTrang;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ngayDat")
    @Temporal(TemporalType.DATE)
    private Date ngayDat;

    public Donhang() {
    }

    public Donhang(Integer maDonHang) {
        this.maDonHang = maDonHang;
    }
    
    public Donhang(Integer maDonHang, String taiKhoan, String hoVaTen, String diaChi, String sdt, String email, float tongThanhToan, String thanhToan, String tinhTrang) {
        this.maDonHang = maDonHang;
        this.taiKhoan = taiKhoan;
        this.hoVaTen = hoVaTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.tongThanhToan = tongThanhToan;
        this.thanhToan = thanhToan;
        this.tinhTrang = tinhTrang;
    }
    
    public Donhang(Integer maDonHang, String taiKhoan, String hoVaTen, String diaChi, String sdt, String email, float tongThanhToan, String thanhToan, String tinhTrang, Date ngayDat) {
        this.maDonHang = maDonHang;
        this.taiKhoan = taiKhoan;
        this.hoVaTen = hoVaTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.tongThanhToan = tongThanhToan;
        this.thanhToan = thanhToan;
        this.tinhTrang = tinhTrang;
        this.ngayDat = ngayDat;
    }

    public Integer getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(Integer maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getTongThanhToan() {
        return tongThanhToan;
    }

    public void setTongThanhToan(float tongThanhToan) {
        this.tongThanhToan = tongThanhToan;
    }

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maDonHang != null ? maDonHang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Donhang)) {
            return false;
        }
        Donhang other = (Donhang) object;
        if ((this.maDonHang == null && other.maDonHang != null) || (this.maDonHang != null && !this.maDonHang.equals(other.maDonHang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Donhang[ maDonHang=" + maDonHang + " ]";
    }
    
}
