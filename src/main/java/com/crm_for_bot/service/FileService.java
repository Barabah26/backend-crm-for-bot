package com.crm_for_bot.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void saveFile(MultipartFile file, Long statementId);
}
