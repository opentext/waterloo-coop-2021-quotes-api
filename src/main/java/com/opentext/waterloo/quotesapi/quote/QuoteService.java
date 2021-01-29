package com.opentext.waterloo.quotesapi.quote;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuoteService implements QuoteRepository {

    @Override
    public List<Quote> findAll() {
        return null;
    }

    @Override
    public List<Quote> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Quote> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Quote> findAllById(Iterable<UUID> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(Quote quote) {

    }

    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Quote> S save(S s) {
        return null;
    }

    @Override
    public <S extends Quote> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Quote> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Quote> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Quote> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Quote getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Quote> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Quote> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Quote> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Quote> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Quote> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Quote> boolean exists(Example<S> example) {
        return false;
    }
}
