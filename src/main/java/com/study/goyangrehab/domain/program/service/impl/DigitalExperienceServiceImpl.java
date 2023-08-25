package com.study.goyangrehab.domain.program.service.impl;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.program.dto.ProgramResponseDto;
import com.study.goyangrehab.domain.program.entity.DigitalExperience;
import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.domain.program.repository.DigitalExperienceRepository;
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
public class DigitalExperienceServiceImpl implements DigitalEducationService {

    private final ProgramRepository programRepository;
    private final DigitalExperienceRepository digitalExperienceRepository;
    @Override
    public List<ProgramResponseDto> getAllInRange() {
        List<Program> programList= programRepository.findAllInRange(ProgramCategory.DIGITAL_EXPERIENCE);

        if (programList.isEmpty()){
            throw new EntityNotFoundException("ProgramList is Empty");
        }

        return programList.stream().map(ProgramResponseDto::new).toList();
    }

    @Override
    public List<ProgramResponseDto> getAllByStatus(ProgramStatus status) {
        List<DigitalExperience> digitalExperienceList = digitalExperienceRepository.findAllByStatusIs(status).orElseThrow(() -> new EntityNotFoundException("digitalExperience List is Empty"));

        return digitalExperienceList.stream().map(ProgramResponseDto::new).toList();
    }

    @Override
    public ProgramResponseDto createProgram(ProgramRequestDto programRequestDto) {
        Program program = ProgramRequestDto.toEntity(programRequestDto);

        DigitalExperience digitalExperience = DigitalExperience.builder()

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

        programRepository.save(digitalExperience);
        return new ProgramResponseDto(digitalExperience);
    }

    @Override
    public ProgramResponseDto updateProgram(Long programId, ProgramRequestDto programRequestDto) {
        DigitalExperience digitalExperience = digitalExperienceRepository.findById(programId).orElseThrow(() -> new EntityNotFoundException("해당 프로그램이 존재하지 않습니다 program_id : " + programId));
        digitalExperience.update(programRequestDto);

        digitalExperienceRepository.save(digitalExperience);

        return new ProgramResponseDto(digitalExperience);
    }
}
