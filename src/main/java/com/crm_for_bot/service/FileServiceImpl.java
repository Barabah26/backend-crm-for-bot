package com.crm_for_bot.service;

import com.crm_for_bot.entity.FileEntity;
import com.crm_for_bot.entity.StatementInfo;
import com.crm_for_bot.repository.FileRepository;
import com.crm_for_bot.repository.StatementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final StatementRepository statementInfoRepository;

    @Override
    public void saveFile(MultipartFile file, Long statementId) {
        StatementInfo statement = statementInfoRepository.findById(statementId)
                .orElseThrow(() -> new RuntimeException("Statement not found with id: " + statementId));

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        try {
            fileEntity.setData(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileEntity.setStatement(statement);

        Long savedFileId = fileRepository.saveFile(fileEntity.getData(), fileEntity.getFileName(), fileEntity.getFileType(), statementId);
        if (savedFileId == null) {
            throw new RuntimeException("Failed to save the file.");
        }
    }
}

