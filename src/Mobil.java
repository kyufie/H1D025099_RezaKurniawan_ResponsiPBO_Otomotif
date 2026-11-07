public class Mobil extends Kendaraan implements BisaMuatPenumpang, BisaMuatBarang {

    private static final int BATAS_SERVIS_KM = 5000;
    private int kapasitasPenumpang;
    private double kapasitasBarangKg;

    public Mobil(String plat, String merek, String model, double tarif,
                 int kapasitasPenumpang, double kapasitasBarangKg) {
        super(plat, merek, model, tarif);
        this.kapasitasPenumpang = kapasitasPenumpang;
        this.kapasitasBarangKg = kapasitasBarangKg;
    }

    // Getter ini sekaligus implementasi interface
    @Override
    public int getKapasitasPenumpang() { return kapasitasPenumpang; }
    public void setKapasitasPenumpang(int kapasitasPenumpang) { this.kapasitasPenumpang = kapasitasPenumpang; }

    @Override
    public double getKapasitasBarangKg() { return kapasitasBarangKg; }
    public void setKapasitasBarangKg(double kapasitasBarangKg) { this.kapasitasBarangKg = kapasitasBarangKg; }

    @Override
    public String getJenis() { return "Mobil"; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("      -> muat " + kapasitasPenumpang + " penumpang, bagasi "
                + kapasitasBarangKg + " kg");
    }

    @Override
    public void periksaServis() {
        if (hitungJarakSejakServis() >= BATAS_SERVIS_KM) {
            setButuhServis(true);
        } else {
            setButuhServis(false);
        }
    }
}
