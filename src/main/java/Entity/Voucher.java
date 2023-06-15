/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author letua
 */
@Entity
@Table(name = "voucher")
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v"),
    @NamedQuery(name = "Voucher.findByMaVoucher", query = "SELECT v FROM Voucher v WHERE v.maVoucher = :maVoucher"),
    @NamedQuery(name = "Voucher.findByPhanTram", query = "SELECT v FROM Voucher v WHERE v.phanTram = :phanTram"),
    @NamedQuery(name = "Voucher.findBySoLuong", query = "SELECT v FROM Voucher v WHERE v.soLuong = :soLuong")})
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "maVoucher")
    private String maVoucher;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phanTram")
    private int phanTram;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soLuong")
    private int soLuong;

    public Voucher() {
    }

    public Voucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public Voucher(String maVoucher, int phanTram, int soLuong) {
        this.maVoucher = maVoucher;
        this.phanTram = phanTram;
        this.soLuong = soLuong;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public int getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(int phanTram) {
        this.phanTram = phanTram;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maVoucher != null ? maVoucher.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.maVoucher == null && other.maVoucher != null) || (this.maVoucher != null && !this.maVoucher.equals(other.maVoucher))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Voucher{" + "maVoucher=" + maVoucher + ", phanTram=" + phanTram + ", soLuong=" + soLuong + '}';
    }

    
    
}
