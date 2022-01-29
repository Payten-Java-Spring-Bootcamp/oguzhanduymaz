package com.homework.bootcamp.adapter.jpa.actor;

import com.homework.bootcamp.domain.actor.Actor;
import com.homework.bootcamp.domain.exception.ExceptionType;
import com.homework.bootcamp.domain.exception.PatikaDataNotFoundException;
import com.homework.bootcamp.domain.port.ActorPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorJpaAdapter implements ActorPersistencePort {

    private final ActorJpaRepository actorJpaRepository;

    @Override
    public Long save(Actor entity) {
        ActorEntity saved = actorJpaRepository.save(ActorEntity.from(entity));
        return saved.getId();
    }

    @Override
    public Actor retrieve(Long id) {
        Optional<ActorEntity> actor = actorJpaRepository.findById(id);
        if (actor.isPresent()){
            return actor.get().toModel();
        }else{
            throw new PatikaDataNotFoundException(ExceptionType.ACTOR_DATA_NOT_FOUND);
        }
    }

    @Override
    public List<Actor> retrieveAll() {
        return actorJpaRepository.findAll()
                .stream()
                .map(ActorEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long Id) {
        actorJpaRepository.deleteById(Id);
    }

    @Override
    public List<Actor> retrieve(List<Long> actorIds) {
        return actorJpaRepository.findAllByIdIn(actorIds).stream()
                .map(ActorEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Actor> create(List<Actor> actors) {
        List<ActorEntity> actorEntities = actors.stream()
                .map(ActorEntity::from)
                .collect(Collectors.toList());
        return actorJpaRepository.saveAll(actorEntities)
                .stream()
                .map(ActorEntity::toModel)
                .collect(Collectors.toList());
    }
}
