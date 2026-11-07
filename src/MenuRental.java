import java.util.ArrayList;
import java.util.Scanner;

/** Antarmuka konsol */
public class MenuRental {

    private ArmadaRental armada;
    private Scanner input;

    public MenuRental(ArmadaRental armada) {
        this.armada = armada;
        this.input = new Scanner(System.in);
    }

    public void jalankan() {
        int pilihan;
        do {
            System.out.println("\n=== RENTAL KENDARAAN ===");
            System.out.println("1. Lihat semua kendaraan");
            System.out.println("2. Cari kendaraan berdasarkan kriteria");
            System.out.println("3. Sewa kendaraan");
            System.out.println("4. Kembalikan kendaraan");
            System.out.println("5. Servis kendaraan");
            System.out.println("0. Keluar");
            pilihan = bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1: menuLihatSemua(); break;
                case 2: menuCari(); break;
                case 3: menuSewa(); break;
                case 4: menuKembali(); break;
                case 5: menuServis(); break;
                case 0: System.out.println("Terima kasih!"); break;
                default: System.out.println("Menu tidak dikenal.");
            }
        } while (pilihan != 0);
    }

    private void menuLihatSemua() {
        System.out.println("\n=== SEMUA KENDARAAN ===");
        tampilkanDaftar(armada.getSemua());
    }

    private void menuCari() {
        System.out.println("\n=== CARI KENDARAAN ===");
        System.out.println("1. Muat minimal X penumpang");
        System.out.println("2. Muat minimal X kg barang");
        System.out.println("3. Muat penumpang DAN barang");
        System.out.println("0. Batal");
        int kriteria = bacaInt("Pilih kriteria: ");

        ArrayList<Kendaraan> hasil;
        if (kriteria == 1) {
            hasil = armada.cariByPenumpang(bacaInt("Minimal penumpang: "));
        } else if (kriteria == 2) {
            hasil = armada.cariByBarang(bacaInt("Minimal barang (kg): "));
        } else if (kriteria == 3) {
            int penumpang = bacaInt("Minimal penumpang: ");
            int kg = bacaInt("Minimal barang (kg): ");
            hasil = armada.cariByPenumpangDanBarang(penumpang, kg);
        } else {
            return;
        }

        System.out.println("\n=== HASIL PENCARIAN ===");
        if (hasil.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang cocok.");
        } else {
            tampilkanDaftar(hasil);
        }
    }

    private void menuSewa() {
        Kendaraan k = pilihDariDaftar(armada.getTersedia(), "PILIH KENDARAAN UNTUK DISEWA");
        if (k == null) return;

        int hari = bacaInt("Jumlah hari sewa: ");
        if (k.sewa(hari)) {
            System.out.println("Berhasil menyewa " + k.getJenis() + " " + k.getMerek()
                    + " " + k.getModel() + " selama " + hari + " hari.");
            System.out.println("Estimasi biaya: " + Kendaraan.rupiah(k.hitungBiayaSewa(hari)));
        } else {
            System.out.println("Gagal menyewa: jumlah hari harus lebih dari 0.");
        }
    }

    private void menuKembali() {
        ArrayList<Kendaraan> disewa = armada.getSedangDisewa();
        if (disewa.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang sedang disewa.");
            return;
        }
        Kendaraan k = pilihDariDaftar(disewa, "PILIH KENDARAAN YANG DIKEMBALIKAN");
        if (k == null) return;

        int hariSewa = k.getHariSewaAktif();
        System.out.println("Kendaraan ini disewa untuk " + hariSewa + " hari.");
        int hariPakai = bacaInt("Berapa hari kendaraan benar-benar dipakai? ");
        while (hariPakai <= 0) {
            System.out.println("Hari pemakaian harus lebih dari 0.");
            hariPakai = bacaInt("Berapa hari kendaraan benar-benar dipakai? ");
        }

        int jarak = bacaInt("Jarak tempuh selama penyewaan (km): ");
        while (jarak < 0) {
            System.out.println("Jarak tidak boleh negatif.");
            jarak = bacaInt("Jarak tempuh selama penyewaan (km): ");
        }

        double biayaDasar = k.hitungBiayaSewa(hariSewa);
        double total = k.kembalikan(hariPakai, jarak);

        System.out.println("\n--- Rincian ---");
        System.out.println("Biaya sewa (" + hariSewa + " hari): " + Kendaraan.rupiah(biayaDasar));
        if (hariPakai > hariSewa) {
            System.out.println("Denda telat (" + (hariPakai - hariSewa) + " hari): "
                    + Kendaraan.rupiah(total - biayaDasar));
        } else {
            System.out.println("Denda: tidak ada");
        }
        System.out.println("TOTAL: " + Kendaraan.rupiah(total));

        if (k.isButuhServis()) {
            System.out.println("\nPERINGATAN: total jarak tempuh " + k.getJarakTotalKm()
                    + " km. Kendaraan ini perlu servis rutin.");
        }
    }

    private void menuServis() {
        ArrayList<Kendaraan> perluServis = armada.getButuhServis();
        if (perluServis.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang perlu servis.");
            return;
        }
        Kendaraan k = pilihDariDaftar(perluServis, "PILIH KENDARAAN YANG DISERVIS");
        if (k == null) return;

        k.servis();
        System.out.println("Servis tercatat pada odometer " + k.getJarakTotalKm()
                + " km. Hitungan servis dimulai dari 0.");
    }

    // ---------- Helper ----------

    private void tampilkanDaftar(ArrayList<Kendaraan> daftar) {
        for (int i = 0; i < daftar.size(); i++) {
            Kendaraan k = daftar.get(i);
            System.out.print((i + 1) + ". [" + k.getJenis() + "] ");
            k.tampilkanInfo();
        }
    }

    /** Nomor yang diketik = nomor urut di daftar yang tampil. 0 = batal. */
    private Kendaraan pilihDariDaftar(ArrayList<Kendaraan> daftar, String judul) {
        if (daftar.isEmpty()) {
            System.out.println("Tidak ada kendaraan pada daftar ini.");
            return null;
        }
        System.out.println("\n=== " + judul + " ===");
        tampilkanDaftar(daftar);
        System.out.println("0. Batal");

        int n = bacaInt("Pilih nomor: ");
        while (n < 0 || n > daftar.size()) {
            System.out.println("Nomor harus antara 0 dan " + daftar.size() + ".");
            n = bacaInt("Pilih nomor: ");
        }
        if (n == 0) {
            return null;
        }
        return daftar.get(n - 1);
    }

    private int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat.");
            }
        }
    }
}
