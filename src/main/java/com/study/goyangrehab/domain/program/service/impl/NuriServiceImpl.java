package com.study.goyangrehab.domain.program.service.impl;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.entity.Arte;
import com.study.goyangrehab.domain.program.entity.NuriClass;
import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.program.repository.ArteRepository;
import com.study.goyangrehab.domain.program.repository.NuriRepository;
import com.study.goyangrehab.domain.program.repository.ProgramRepository;
import com.study.goyangrehab.domain.program.service.NuriService;
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
@Service
@RequiredArgsConstructor
@Transactional
public class NuriServiceImpl implements NuriService {
    static final Logger logger = LogManager.getLogger(NuriServiceImpl.class);
    private final ProgramRepository programRepository;
    private final NuriRepository nuriRepository;

    @Override
    public List<ProgramResponseDto> getAllInRange() {
        List<Program> programList= programRepository.findAllInRange(ProgramCategory.ARTE);

        if (programList.isEmpty()){
            throw new EntityNotFoundException("ProgramList is Empty");
        }

        return programList.stream().map(ProgramResponseDto::new).toList();
    }

    @Override
    public List<ProgramResponseDto> getAllByStatus(ProgramStatus status) {
        List<NuriClass> nuriList = nuriRepository.findAllByStatusIs(status).orElseThrow(() -> new EntityNotFoundException("NuriClass List is Empty"));

        return nuriList.stream().map(ProgramResponseDto::new).toList();
    }

    @Override
    public ProgramResponseDto createProgram(ProgramRequestDto programRequestDto) {
        int disabledNum = programRequestDto.getDisabledNum();
        int nonDisabledNum = programRequestDto.getNonDisabledNum();
        String applicationTarget = programRequestDto.getApplicationTarget();
        Program program = ProgramRequestDto.toEntity(programRequestDto);

        NuriClass nuri = NuriClass.builder()
                .disabledNum(disabledNum)
                .nonDisabledNum(nonDisabledNum)
                .applicationTarget(applicationTarget)
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

        programRepository.save(nuri);
        return new ProgramResponseDto(nuri);
    }

    @Override
    public ProgramResponseDto updateProgram(Long programId, ProgramRequestDto programRequestDto) {
        NuriClass nuri = nuriRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException("해당 프로그램이 존재하지 않습니다 program_id : " + programId));
        nuri.update(programRequestDto);

        nuriRepository.save(nuri);

        return new ProgramResponseDto(nuri);
    }

}
