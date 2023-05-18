package com.MADG2.service.circuits;

import com.MADG2.common.Constants;
import com.MADG2.dao.CircuitDao;
import com.MADG2.model.entities.Circuit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CircuitServiceImpl implements CircuitService{
    @Autowired
    CircuitDao circuitDao;
    @Override
    public Page<Circuit> findAll(Pageable pageable) {
        return circuitDao.findAll(pageable);
    }

    @Override
    public void delete(long id) {
        circuitDao.deleteById(id);
    }

    @Override
    public void save(Circuit circuit) {
        String image = circuit.getImage();
        circuit.setImage(Constants.UPLOAD_LINK+'/'+image);
        circuitDao.save(circuit);
    }

    @Override
    public Circuit findById(long id) {
        return circuitDao.findById(id);
    }

    @Override
    public void update(Long id, Circuit circuit) throws ChangeSetPersister.NotFoundException {
        Circuit circuitEntity = circuitDao.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        circuitEntity.setImage(Constants.UPLOAD_LINK+'/'+circuit.getImage());
        circuitEntity.setCity(circuit.getCity());
        circuitEntity.setLaps(circuit.getLaps());
        circuitEntity.setEvent(circuit.getEvent());
        circuitEntity.setName(circuit.getName());
        circuitEntity.setMeters(circuit.getMeters());
        circuitEntity.setFastCurves(circuit.getFastCurves());
        circuitEntity.setMediumCurves(circuit.getMediumCurves());
        circuitEntity.setSlowCurves(circuit.getSlowCurves());
        circuitEntity.setCountry(circuit.getCountry());

        circuitDao.save(circuitEntity);

    }


}
