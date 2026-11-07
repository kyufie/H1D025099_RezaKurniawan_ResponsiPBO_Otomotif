public class Main {

    public static void main(String[] args) {
        ArmadaRental armada = new ArmadaRental();
        isiDataAwal(armada);
        new MenuRental(armada).jalankan();
    }

    private static void isiDataAwal(ArmadaRental armada) {
        // Konstruktor 1: kendaraan baru (odometer 0)
        armada.tambah(new Motor("R 1111 AA", "Honda", "Beat", 80000));
        // Konstruktor 2: kendaraan bekas dengan odometer awal 1.800 km
        armada.tambah(new Motor("R 2222 BB", "Yamaha", "NMAX", 130000, 1800));
        armada.tambah(new Mobil("R 3333 CC", "Toyota", "Avanza", 300000, 7, 300));
        armada.tambah(new Mobil("R 4444 DD", "Toyota", "Hilux", 600000, 5, 1000));
        armada.tambah(new Truk("R 5555 EE", "Mitsubishi", "Colt Diesel", 700000, 3000));
        armada.tambah(new Truk("R 6666 FF", "Hino", "Dutro", 1100000, 8000));
    }
}
