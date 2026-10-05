import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StatistikNilai {
    // Ketentuan 2: Menggunakan sentinel static final int SELESAI = -1
    private static final int SELESAI = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Ketentuan 1: Simpan di ArrayList<Integer>
        ArrayList<Integer> daftarNilai = new ArrayList<>();

        System.out.println("===== STATISTIK NILAI KELAS =====");
        System.out.println("Ketik -1 kalau sudah selesai.");

        int inputVal;
        // Ketentuan 2: Pembacaan memakai do-while
        do {
            // Ketentuan 1: Nomor prompt diambil dari size() + 1, bukan variabel pencacah terpisah
            System.out.print("Nilai ke-" + (daftarNilai.size() + 1) + " : ");
            inputVal = scanner.nextInt();

            if (inputVal == SELESAI) {
                break;
            }

            // Ketentuan 3: Nilai di luar 0-100 ditolak dengan continue dan tidak di-add
            if (inputVal < 0 || inputVal > 100) {
                System.out.println("    Ditolak, harus 0-100");
                continue;
            }

            daftarNilai.add(inputVal);

        } while (inputVal != SELESAI);

        System.out.println();

        // Ketentuan 8: Menangani kasus tidak ada nilai sah sama sekali (langsung -1)
        if (daftarNilai.isEmpty()) {
            System.out.println("Tidak ada nilai sah yang dimasukkan.");
            scanner.close();
            return;
        }

        // Tampilkan daftar nilai tersimpan & Jumlah
        System.out.println("Nilai tersimpan : " + daftarNilai);
        System.out.println("Jumlah          : " + daftarNilai.size());

        // Hitung Total dan Rata-rata
        double total = 0;
        for (int nilai : daftarNilai) {
            total += nilai;
        }
        double rataRata = total / daftarNilai.size();

        // Cetak Rata-rata dengan format koma (Locale Indonesia)
        System.out.printf("Rata-rata       : %.2f%n", rataRata);

        /*
         * Ketentuan 4:
         * Tertinggi dan terendah dicari dengan loop sendiri, bukan dengan Collections.max / min.
         * ALASAN PEMILIHAN NILAI AWAL:
         * Nilai awal tertinggi dan terendah diinisialisasi dari elemen pertama daftar (daftarNilai.get(0)).
         * Hal ini dilakukan agar patokan nilai pembanding berasal dari data riil yang ada dalam daftar,
         * sehingga tidak terjadi kesalahan batas akibat penggunaan angka asumsi sembarang (seperti 0 atau 100).
         */
        int tertinggi = daftarNilai.get(0);
        int terendah = daftarNilai.get(0);

        for (int i = 1; i < daftarNilai.size(); i++) {
            int current = daftarNilai.get(i);
            if (current > tertinggi) {
                tertinggi = current;
            }
            if (current < terendah) {
                terendah = current;
            }
        }

        System.out.println("Tertinggi       : " + tertinggi);
        System.out.println("Terendah        : " + terendah);

        /*
         * Ketentuan 5:
         * Jumlah di atas rata-rata dihitung dengan putaran kedua (loop tersendiri), sesudah rata-ratanya diketahui.
         * ALASAN TIDAK BISA DIHITUNG DI PUTARAN PERTAMA:
         * Rata-rata kelas belum bisa diketahui sebelum SELURUH data nilai selesai dibaca dan ditotal.
         * Karena patokan pembanding (nilai rata-rata) baru didapatkan setelah input selesai,
         * maka penentuan apakah suatu nilai berada di atas rata-rata harus dilakukan pada iterasi/putaran kedua.
         */
        int diAtasRata = 0;
        for (int nilai : daftarNilai) {
            if (nilai > rataRata) {
                diAtasRata++;
            }
        }
        System.out.println("Di atas rata2   : " + diAtasRata + " orang");

        /*
         * Ketentuan 6:
         * Distribusi grade disimpan di array int[] jumlahGrade = new int[5];
         * Index 0 = A (85-100), Index 1 = B (70-84), Index 2 = C (55-69), Index 3 = D (40-54), Index 4 = E (0-39).
         * Dicetak menggunakan loop, bukan lima baris println.
         */
        int[] jumlahGrade = new int[5];
        for (int nilai : daftarNilai) {
            if (nilai >= 85) {
                jumlahGrade[0]++; // Grade A
            } else if (nilai >= 70) {
                jumlahGrade[1]++; // Grade B
            } else if (nilai >= 55) {
                jumlahGrade[2]++; // Grade C
            } else if (nilai >= 40) {
                jumlahGrade[3]++; // Grade D
            } else {
                jumlahGrade[4]++; // Grade E
            }
        }

        char[] namaGrade = {'A', 'B', 'C', 'D', 'E'};
        System.out.print("Distribusi      : ");
        for (int i = 0; i < jumlahGrade.length; i++) {
            System.out.print(namaGrade[i] + "=" + jumlahGrade[i]);
            if (i < jumlahGrade.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        /*
         * Ketentuan 7:
         * Daftar terurut dibuat dari salinan ArrayList<Integer> terurut = new ArrayList<>(daftar);
         * Lalu diurutkan dengan Collections.sort(terurut).
         * Urutan asli tetap dicetak untuk membuktikan daftar asli tidak ikut terurut.
         */
        ArrayList<Integer> terurut = new ArrayList<>(daftarNilai);
        Collections.sort(terurut);

        System.out.println("Terurut         : " + terurut);
        System.out.println("Urutan asli     : " + daftarNilai);

        scanner.close();
    }
}