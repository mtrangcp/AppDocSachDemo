package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

public class ArraySachChiTiet {
    Sach2 data;

    public ArraySachChiTiet(Sach2 data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ArraySachChiTiet{" +
                "data=" + data +
                '}';
    }

    public Sach2 getData() {
        return data;
    }

    public void setData(Sach2 data) {
        this.data = data;
    }

    public ArraySachChiTiet() {
    }
}
