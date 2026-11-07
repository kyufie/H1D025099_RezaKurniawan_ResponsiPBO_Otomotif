public class Truk extends Kendaraan implements BisaMuatBarang {

    private static final int BATAS_SERVIS_KM = 10000;
    private double kapasitasBarangKg;

    public Truk(String plat, String merek, String model, double tarif, double kapasitasBarangKg) {
        super(plat, merek, model, tarif);
        this.kapasitasBarangKg = kapasitasBarangKg;
    }

    // Getter ini sekaligus implementasi interface
    @Override
    public double getKapasitasBarangKg() { return kapasitasBarangKg; }
    public void setKapasitasBarangKg(double kapasitasBarangKg) { this.kapasitasBarangKg = kapasitasBarangKg; }

    @Override
    public String getJenis() { return "Truk"; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("      -> muatan " + kapasitasBarangKg + " kg");
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
