package com.pp.article.service;

import com.pp.article.domain.FileLinkDomain;

public interface IFileLinkService {

    int save(FileLinkDomain domain);

    int update(FileLinkDomain domain);

    int deleteByRefId(String refId);

    FileLinkDomain getByFileId(String fileId);
}
