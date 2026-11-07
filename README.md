# Sistem Sewa Kendaraan (Responsi Akhir PBO)

## Identitas
- Nama : Reza Kurniawan
- NIM  : H1D025099
- Tema : Otomotif

## Deskripsi
Sistem sewa kendaraan berbasis konsol. Jenis kendaraan: Motor, Mobil, Truk.
Kapabilitas dimodelkan dengan interface `BisaMuatPenumpang` dan `BisaMuatBarang`,
sehingga kendaraan dapat dicari berdasarkan kapasitas penumpang dan/atau barang.

## Compile dan Run (JDK 8 atau lebih baru)
```bash
mkdir out
javac -d out src/*.java
java -cp out Main
```

## Screenshot

### Lihat Semua Kendaraan

![Lihat semua kendaraan](screenshots/lihat-kendaraan.png)

### Cari Kendaraan Berdasarkan Kebutuhan

![Cari kendaraan berdasarkan kebutuhan](screenshots/cari.png)

### Alur Sewa dan Pengembalian

![Alur sewa dan pengembalian](screenshots/sewa-kembali.png)

### Servis Kendaraan

![Servis kendaraan](screenshots/servis.png)
