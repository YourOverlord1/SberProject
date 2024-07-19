package sber.project.service;

import sber.project.entity.ArchiveTask;
import sber.project.entity.User;
import sber.project.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArchiveService {
    private ArchiveRepository archiveRepository;

    @Autowired
    public void ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Transactional(readOnly = true)
    public List<ArchiveTask> getAllArchive(User user) {
        List<ArchiveTask> bases = (List<ArchiveTask>) archiveRepository.findAllByUser(user);
        if (bases.size() > 0) {
            return bases;
        } else {
            return new ArrayList<ArchiveTask>();
        }
    }

    @Transactional(readOnly = true)
    public ArchiveTask getArchiveById(int id, User user) {
        return archiveRepository.findByIdAndUser(id, user);
    }

    @Transactional
    public ArchiveTask createOrUpdateArchive(ArchiveTask archiveTask, User user) {
        archiveTask = archiveRepository.save(archiveTask);
        return archiveTask;
    }

    @Transactional
    public void deleteTaskArchiveById(int id,User user) {
        ArchiveTask base = archiveRepository.findByIdAndUser(id, user);
    }
}