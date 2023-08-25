package com.study.goyangrehab.domain.program.service.impl;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.entity.Arte;
import com.study.goyangrehab.domain.program.entity.DigitalEducation;
import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.program.repository.DigitalEducationRepository;
import com.study.goyangrehab.domain.program.repository.ProgramRepository;
import com.study.goyangrehab.domain.program.service.DigitalEducationService;
import com.study.goyangrehab.enums.ProgramCategory;
import com.study.goyangrehab.enums.ProgramStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class DigitalEducationServiceImpl implements DigitalEducationService {

    private final ProgramRepository programRepository;
    private final DigitalEducationRepository digitalEducationRepository;

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
        List<DigitalEducation> arteList = digitalEducationRepository.findAllByStatusIs(status).orElseThrow(() -> new EntityNotFoundException("DIGITAL_EDUCATION List is Empty"));

        return arteList.stream().map(ProgramResponseDto::new).toList();
    }

    @Override
    public ProgramResponseDto createProgram(ProgramRequestDto programRequestDto) {
        Program program = ProgramRequestDto.toEntity(programRequestDto);

        DigitalEducation digitalEducation = DigitalEducation.builder()
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

        programRepository.save(digitalEducation);
        return new ProgramResponseDto(digitalEducation);
    }

    @Override
    public ProgramResponseDto updateProgram(Long programId, ProgramRequestDto programRequestDto) {
        DigitalEducation digitalEducation = digitalEducationRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException("해당 프로그램이 존재하지 않습니다 program_id : " + programId));
        digitalEducation.update(programRequestDto);

        digitalEducationRepository.save(digitalEducation);

        return new ProgramResponseDto(digitalEducation);
    }


}
