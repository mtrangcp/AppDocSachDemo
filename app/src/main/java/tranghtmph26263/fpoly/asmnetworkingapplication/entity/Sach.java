package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

import java.util.ArrayList;

public class Sach {
    private String  _id;
    private int __v;
    private String tenSach, moTa, tacGia, anhBia;
    private int namsx;
    private ArrayList<String> binhLuan;

    public Sach() {
    }

    public Sach(String _id, int __v, String tenSach, String moTa, String tacGia, String anhBia, int namsx, ArrayList<String> binhLuan) {
        this._id = _id;
        this.__v = __v;
        this.tenSach = tenSach;
        this.moTa = moTa;
        this.tacGia = tacGia;
        this.anhBia = anhBia;
        this.namsx = namsx;
        this.binhLuan = binhLuan;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "_id='" + _id + '\'' +
                ", __v=" + __v +
                ", tenSach='" + tenSach + '\'' +
                ", moTa='" + moTa + '\'' +
                ", tacGia='" + tacGia + '\'' +
                ", anhBia='" + anhBia + '\'' +
                ", namsx=" + namsx +
                ", binhLuan=" + binhLuan +
                '}';
    }

    public int get__v() {
        return __v;
    }

    public String get_id() {
        return _id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }

    public int getNamsx() {
        return namsx;
    }

    public void setNamsx(int namsx) {
        this.namsx = namsx;
    }

    public ArrayList<String> getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(ArrayList<String> binhLuan) {
        this.binhLuan = binhLuan;
    }
}
