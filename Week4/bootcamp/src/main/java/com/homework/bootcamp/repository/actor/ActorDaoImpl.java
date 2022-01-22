package com.homework.bootcamp.repository.actor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorDaoImpl implements ActorDao{

    private final ActorJpaRepository actorJpaRepository;

    @Override
    public Long save(ActorEntity entity) {
        ActorEntity saved = actorJpaRepository.save(entity);
        return saved.getId();
    }

    @Override
    public ActorEntity retrieve(Long id) {
        Optional<ActorEntity> actor = actorJpaRepository.findById(id);
        if (actor.isPresent()){
            return actor.get();
        }else{
            throw new RuntimeException();
        }
    }

    @Override
    public List<ActorEntity> retrieveAll() {
        return actorJpaRepository.findAll();
    }

    @Override
    public void delete(Long Id) {
        actorJpaRepository.deleteById(Id);
    }

    @Override
    public List<ActorEntity> retrieve(List<Long> actorIds) {
        return actorJpaRepository.findAllByIdIn(actorIds);
    }

    @Override
    public List<ActorEntity> create(List<ActorEntity> actors) {
        return actorJpaRepository.saveAll(actors);
    }
}
