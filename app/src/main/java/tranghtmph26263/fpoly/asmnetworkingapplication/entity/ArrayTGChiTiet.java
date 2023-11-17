package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

public class ArrayTGChiTiet {
    private   TacGia2 data;

    public ArrayTGChiTiet(TacGia2 data) {
        this.data = data;
    }

    public ArrayTGChiTiet() {
    }

    @Override
    public String toString() {
        return "ArrayTGChiTiet{" +
                "data=" + data +
                '}';
    }

    public TacGia2 getData() {
        return data;
    }

    public void setData(TacGia2 data) {
        this.data = data;
    }
}
