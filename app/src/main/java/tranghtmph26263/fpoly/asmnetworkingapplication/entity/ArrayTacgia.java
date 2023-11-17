package tranghtmph26263.fpoly.asmnetworkingapplication.entity;

import java.util.Arrays;

public class ArrayTacgia {
    private TacGia[] data;

    public ArrayTacgia(TacGia[] data) {
        this.data = data;
    }

    public TacGia[] getData() {
        return data;
    }

    public void setData(TacGia[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ArrayTacgia{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
