package md.service;

import md.entity.EmployeesInfo;
import md.hibernate.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final PersonDao personDao;

    @Autowired
    public EmployeeService(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Transactional
    public void saveEmployee(EmployeesInfo employee) {
        personDao.save(employee);
    }

    @Transactional
    public EmployeesInfo getEmployeeById(Long id) {
        return personDao.findById(id);
    }

    @Transactional
    public List<EmployeesInfo> getAllEmployees() {
        return personDao.findAll();
    }

    @Transactional
    public void updateEmployee(EmployeesInfo employee) {
        personDao.update(employee);
    }

    @Transactional
    public void deleteEmployee(EmployeesInfo employee) {
        personDao.delete(employee);
    }
}
