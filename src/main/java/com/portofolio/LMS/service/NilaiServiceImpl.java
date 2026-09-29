package com.portofolio.LMS.service;

import com.portofolio.LMS.dto.NilaiAkhirDTO;
import com.portofolio.LMS.dto.NilaiRequestDTO;
import com.portofolio.LMS.dto.NilaiResponseDTO;
import com.portofolio.LMS.exception.ResourceNotFoundException;
import com.portofolio.LMS.model.*;
import com.portofolio.LMS.repository.BobotPenilaianRepository;
import com.portofolio.LMS.repository.EnrollmentRepository;
import com.portofolio.LMS.repository.GuruRepository;
import com.portofolio.LMS.repository.NilaiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NilaiServiceImpl implements NilaiService {

    private final NilaiRepository nilaiRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final GuruRepository guruRepository;
    private final BobotPenilaianRepository bobotPenilaianRepository;

    @Override
    @Transactional
    public NilaiResponseDTO createNilai(NilaiRequestDTO request) {
        Enrollment enrollment = enrollmentRepository.findById(request.getEnrollmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment tidak ditemukan dengan id: " + request.getEnrollmentId()));

        Guru guru = guruRepository.findById(request.getGuruId())
                .orElseThrow(() -> new ResourceNotFoundException("Guru tidak ditemukan dengan id: " + request.getGuruId()));

        Nilai nilai = Nilai.builder()
                .enrollment(enrollment)
                .guru(guru)
                .mataPelajaran(request.getMataPelajaran())
                .jenis(request.getJenis())
                .nilai(request.getNilai())
                .tanggal(request.getTanggal())
                .keterangan(request.getKeterangan())
                .build();

        return NilaiResponseDTO.fromEntity(nilaiRepository.save(nilai));
    }

    @Override
    public NilaiResponseDTO getNilaiById(Long id) {
        Nilai nilai = nilaiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nilai tidak ditemukan dengan id: " + id));
        return NilaiResponseDTO.fromEntity(nilai);
    }

    @Override
    public List<NilaiResponseDTO> getNilaiByEnrollmentId(Long enrollmentId) {
        return nilaiRepository.findByEnrollmentId(enrollmentId).stream()
                .map(NilaiResponseDTO::fromEntity).toList();
    }

    @Override
    @Transactional
    public NilaiResponseDTO updateNilai(Long id, NilaiRequestDTO request) {
        Nilai nilai = nilaiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nilai tidak ditemukan dengan id: " + id));

        nilai.setJenis(request.getJenis());
        nilai.setNilai(request.getNilai());
        nilai.setTanggal(request.getTanggal());
        nilai.setKeterangan(request.getKeterangan());

        return NilaiResponseDTO.fromEntity(nilaiRepository.save(nilai));
    }

    @Override
    @Transactional
    public void deleteNilai(Long id) {
        if (!nilaiRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nilai tidak ditemukan dengan id: " + id);
        }
        nilaiRepository.deleteById(id);
    }

    @Override
    public NilaiAkhirDTO hitungNilaiAkhir(Long enrollmentId, String mataPelajaran) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment tidak ditemukan dengan id: " + enrollmentId));

        List<Nilai> semuaNilai = nilaiRepository.findByEnrollmentIdAndMataPelajaran(enrollmentId, mataPelajaran);

        Map<JenisNilai, List<Nilai>> nilaiPerJenis = semuaNilai.stream()
                .collect(Collectors.groupingBy(Nilai::getJenis));

        List<BobotPenilaian> semuaBobot = bobotPenilaianRepository.findByMataPelajaran(mataPelajaran);

        List<NilaiAkhirDTO.RincianKategori> rincian = new ArrayList<>();
        BigDecimal totalNilaiAkhir = BigDecimal.ZERO;

        for (BobotPenilaian bobot : semuaBobot) {
            List<Nilai> daftarNilaiJenis = nilaiPerJenis.getOrDefault(bobot.getJenis(), List.of());

            BigDecimal rataRata = daftarNilaiJenis.isEmpty()
                    ? BigDecimal.ZERO
                    : daftarNilaiJenis.stream()
                      .map(Nilai::getNilai)
                      .reduce(BigDecimal.ZERO, BigDecimal::add)
                      .divide(BigDecimal.valueOf(daftarNilaiJenis.size()), 2, RoundingMode.HALF_UP);

            BigDecimal kontribusi = rataRata
                    .multiply(BigDecimal.valueOf(bobot.getBobot()))
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            totalNilaiAkhir = totalNilaiAkhir.add(kontribusi);

            rincian.add(NilaiAkhirDTO.RincianKategori.builder()
                    .jenis(bobot.getJenis().name())
                    .rataRata(rataRata)
                    .bobot(bobot.getBobot())
                    .kontribusi(kontribusi)
                    .build());
        }

        return NilaiAkhirDTO.builder()
                .enrollmentId(enrollment.getId())
                .siswaNama(enrollment.getSiswa().getFullName())
                .siswaNis(enrollment.getSiswa().getNis())
                .namaKelas(enrollment.getKelas().getNamaKelas())
                .mataPelajaran(mataPelajaran)
                .rincian(rincian)
                .nilaiAkhir(totalNilaiAkhir)
                .build();
    }
}
