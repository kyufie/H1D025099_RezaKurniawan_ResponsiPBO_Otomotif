public class Motor extends Kendaraan implements BisaMuatPenumpang {

    private static final int BATAS_SERVIS_KM = 2000;
    private static final int KAPASITAS_PENUMPANG = 2;

    public Motor(String plat, String merek, String model, double tarif) {
        super(plat, merek, model, tarif);
    }

    public Motor(String plat, String merek, String model, double tarif, int jarakTotalKm) {
        super(plat, merek, model, tarif, jarakTotalKm);
    }

    @Override
    public int getKapasitasPenumpang() { return KAPASITAS_PENUMPANG; }

    @Override
    public String getJenis() { return "Motor"; }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("      -> muat " + KAPASITAS_PENUMPANG + " penumpang");
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
