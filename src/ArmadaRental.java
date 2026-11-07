import java.util.ArrayList;

/** Menyimpan kendaraan dan menyediakan filter. Tidak mencetak apa pun. */
public class ArmadaRental {

    private ArrayList<Kendaraan> daftar;

    public ArmadaRental() {
        this.daftar = new ArrayList<>();
    }

    public void tambah(Kendaraan k) {
        daftar.add(k);
    }

    public ArrayList<Kendaraan> getSemua() {
        return new ArrayList<>(daftar);
    }

    public ArrayList<Kendaraan> getTersedia() {
        ArrayList<Kendaraan> hasil = new ArrayList<>();
        for (Kendaraan k : daftar) {
            if (k.isTersedia()) {
                hasil.add(k);
            }
        }
        return hasil;
    }

    public ArrayList<Kendaraan> getSedangDisewa() {
        ArrayList<Kendaraan> hasil = new ArrayList<>();
        for (Kendaraan k : daftar) {
            if (!k.isTersedia()) {
                hasil.add(k);
            }
        }
        return hasil;
    }

    public ArrayList<Kendaraan> getButuhServis() {
        ArrayList<Kendaraan> hasil = new ArrayList<>();
        for (Kendaraan k : daftar) {
            if (k.isButuhServis()) {
                hasil.add(k);
            }
        }
        return hasil;
    }

    public ArrayList<Kendaraan> cariByPenumpang(int minPenumpang) {
        ArrayList<Kendaraan> hasil = new ArrayList<>();
        for (Kendaraan k : daftar) {
            if (k instanceof BisaMuatPenumpang) {
                BisaMuatPenumpang p = (BisaMuatPenumpang) k;
                if (p.getKapasitasPenumpang() >= minPenumpang) {
                    hasil.add(k);
                }
            }
        }
        return hasil;
    }

    public ArrayList<Kendaraan> cariByBarang(double minKg) {
        ArrayList<Kendaraan> hasil = new ArrayList<>();
        for (Kendaraan k : daftar) {
            if (k instanceof BisaMuatBarang) {
                BisaMuatBarang b = (BisaMuatBarang) k;
                if (b.getKapasitasBarangKg() >= minKg) {
                    hasil.add(k);
                }
            }
        }
        return hasil;
    }

    public ArrayList<Kendaraan> cariByPenumpangDanBarang(int minPenumpang, double minKg) {
        ArrayList<Kendaraan> hasil = new ArrayList<>();
        for (Kendaraan k : daftar) {
            if (k instanceof BisaMuatPenumpang && k instanceof BisaMuatBarang) {
                BisaMuatPenumpang p = (BisaMuatPenumpang) k;
                BisaMuatBarang b = (BisaMuatBarang) k;
                if (p.getKapasitasPenumpang() >= minPenumpang
                        && b.getKapasitasBarangKg() >= minKg) {
                    hasil.add(k);
                }
            }
        }
        return hasil;
    }
}
