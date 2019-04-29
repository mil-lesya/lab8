package com.mileshko.lesya.dao;

import com.mileshko.lesya.entity.Souvenir;
import com.mileshko.lesya.exception.SouvenirDaoException;

import java.util.List;

public interface SouvenirDao {
    public Long addSouvenir(Souvenir manufacturer) throws SouvenirDaoException;
    public void updateSouvenir(Souvenir Manufacturer) throws SouvenirDaoException;
    public void deleteSouvenir(Long id) throws SouvenirDaoException;
    public Souvenir getSouvenir(Long id) throws SouvenirDaoException;
    public List<Souvenir> findSouvenirs() throws SouvenirDaoException;
}
