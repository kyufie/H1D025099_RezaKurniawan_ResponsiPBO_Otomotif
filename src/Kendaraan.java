/** Superclass abstrak untuk semua kendaraan yang bisa disewa. */
public abstract class Kendaraan {

    public static final double DENDA_TELAT_PERSEN = 0.10;

    private final String platNomor;

    private String merek;
    private String model;
    private double tarifPerHari;
    private boolean tersedia;
    private int hariSewaAktif;
    private int jarakTotalKm;
    private int jarakServisTerakhirKm;
    private boolean butuhServis;

    // Kendaraan baru, odometer 0
    public Kendaraan(String platNomor, String merek, String model, double tarifPerHari) {
        this(platNomor, merek, model, tarifPerHari, 0);
    }

    // Kendaraan yang sudah pernah dipakai (odometer awal diketahui)
    public Kendaraan(String platNomor, String merek, String model,
                     double tarifPerHari, int jarakTotalKm) {
        this.platNomor = platNomor;
        this.merek = merek;
        this.model = model;
        this.tarifPerHari = tarifPerHari;
        this.jarakTotalKm = jarakTotalKm;
        this.jarakServisTerakhirKm = jarakTotalKm;
        this.tersedia = true;
        this.hariSewaAktif = 0;
        this.butuhServis = false;
    }

    public String getPlatNomor() { return platNomor; } // final: tanpa setter

    public String getMerek() { return merek; }
    public void setMerek(String merek) { this.merek = merek; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getTarifPerHari() { return tarifPerHari; }
    public void setTarifPerHari(double tarifPerHari) { this.tarifPerHari = tarifPerHari; }

    public boolean isTersedia() { return tersedia; }
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }

    public int getHariSewaAktif() { return hariSewaAktif; }
    public void setHariSewaAktif(int hariSewaAktif) { this.hariSewaAktif = hariSewaAktif; }

    public int getJarakTotalKm() { return jarakTotalKm; }
    public void setJarakTotalKm(int jarakTotalKm) { this.jarakTotalKm = jarakTotalKm; }

    public int getJarakServisTerakhirKm() { return jarakServisTerakhirKm; }
    public void setJarakServisTerakhirKm(int jarakServisTerakhirKm) { this.jarakServisTerakhirKm = jarakServisTerakhirKm; }

    public boolean isButuhServis() { return butuhServis; }
    public void setButuhServis(boolean butuhServis) { this.butuhServis = butuhServis; }

    public void tampilkanInfo() {
        String status = tersedia ? "Tersedia" : "DISEWA (" + hariSewaAktif + " hari)";
        String tagServis = butuhServis ? " | PERLU SERVIS" : "";
        System.out.println(platNomor + " | " + merek + " " + model
                + " | " + rupiah(tarifPerHari) + "/hari | " + status
                + " | " + jarakTotalKm + " km" + tagServis);
    }

    public double hitungBiayaSewa(int hari) {
        return tarifPerHari * hari;
    }

    public double hitungDenda(int hariTelat) {
        return hitungBiayaSewa(hariTelat) * (1 + DENDA_TELAT_PERSEN);
    }

    public int hitungJarakSejakServis() {
        return jarakTotalKm - jarakServisTerakhirKm;
    }

    public void servis() {
        jarakServisTerakhirKm = jarakTotalKm;
        butuhServis = false;
    }

    public boolean sewa(int hari) {
        if (tersedia && hari > 0) {
            tersedia = false;
            hariSewaAktif = hari;
            return true;
        } else {
            return false;
        }
    }

    public double kembalikan(int hariPakai, int jarakKm) {
        double total = hitungBiayaSewa(hariSewaAktif);
        if (hariPakai > hariSewaAktif) {
            total += hitungDenda(hariPakai - hariSewaAktif);
        }
        jarakTotalKm += jarakKm;
        periksaServis();
        hariSewaAktif = 0;
        tersedia = true;
        return total;
    }

    public abstract String getJenis();

    /** Tiap jenis punya batas jarak servis sendiri. */
    public abstract void periksaServis();

    public static String rupiah(double nilai) {
        return "Rp" + String.format("%,.0f", nilai);
    }
}
