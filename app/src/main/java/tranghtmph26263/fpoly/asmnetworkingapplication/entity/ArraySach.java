package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

import java.util.Arrays;

public class ArraySach {

    private Sach[] data;

    public ArraySach(Sach[] data) {
        this.data = data;
    }

    public Sach[] getData() {
        return data;
    }

    public void setData(Sach[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ArraySach{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
