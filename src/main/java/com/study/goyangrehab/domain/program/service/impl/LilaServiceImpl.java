package com.study.goyangrehab.domain.program.service.impl;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.entity.Lila;
import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.program.repository.LilaRepository;
import com.study.goyangrehab.domain.program.repository.ProgramRepository;
import com.study.goyangrehab.domain.program.service.LilaService;
import com.study.goyangrehab.enums.ProgramCategory;
import com.study.goyangrehab.enums.ProgramStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Transactional
@Service
public class LilaServiceImpl implements LilaService {

    static final Logger logger = LogManager.getLogger(LilaServiceImpl.class);
    private final ProgramRepository programRepository;
    private final LilaRepository lilaRepository;

    @Override
    public List<ProgramResponseDto> getAllInRange() {
        List<Program> programList = programRepository.findAllInRange(ProgramCategory.LILA);

        if (programList.isEmpty()) {
            throw new EntityNotFoundException("ProgramList is Empty");
        }

        return programList.stream().map(ProgramResponseDto::new).toList();
    }

    @Override
    public List<ProgramResponseDto> getAllByStatus(ProgramStatus status) {
        List<Lila> lilaList = lilaRepository.findAllByStatusIs(status).orElseThrow(() -> new EntityNotFoundException("Lila List is Empty"));

        return lilaList.stream().map(ProgramResponseDto::new).toList();
    }

    @Override
    public ProgramResponseDto createProgram(ProgramRequestDto programRequestDto) {
        String place = programRequestDto.getPlace();
        Program program = ProgramRequestDto.toEntity(programRequestDto);

        Lila lila = Lila.builder()
                .place(place)
                .name(program.getName())
                .startTime(program.getStartTime())
                .endTime(program.getEndTime())
                .registerStartTime(program.getRegisterStartTime())
                .registerEndTime(program.getRegisterEndTime())
                .text(program.getText())
                .price(program.getPrice())
                .paymentDeadLine(program.getPaymentDeadLine())
                .docDeadLine(program.getDocDeadLine())
                .recruitmentCapacity(program.getRecruitmentCapacity())
                .build();

        programRepository.save(lila);
        return new ProgramResponseDto(lila);
    }

    @Override
    public ProgramResponseDto updateProgram(Long programId, ProgramRequestDto programRequestDto) {
        Lila lila = lilaRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException("해당 프로그램이 존재하지 않습니다 program_id : " + programId));
        lila.update(programRequestDto);

        lilaRepository.save(lila);

        return new ProgramResponseDto(lila);
    }

}
