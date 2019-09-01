package com.dvlcube.app.rest;

import com.dvlcube.app.interfaces.MenuItem;
import com.dvlcube.app.jpa.repo.JobRepository;
import com.dvlcube.app.manager.data.JobBean;
import static com.dvlcube.app.manager.data.e.Menu.CONFIGURATION;
import com.dvlcube.app.manager.data.vo.MxRestResponse;
import com.dvlcube.utils.interfaces.rest.MxFilterableBeanService;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MenuItem(value = CONFIGURATION)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
@RequestMapping("${dvl.rest.prefix}/jobs")
public class JobService implements MxFilterableBeanService<JobBean, Long> {

    @Autowired
    private JobRepository repo;

    @Override
    @GetMapping
    public Iterable<JobBean> get(Map<String, String> params) {
        return repo.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Optional<JobBean> get(@PathVariable Long id) {
        return repo.findById(id);
    }

    @Override
    @PostMapping
    public MxRestResponse post(@Valid @RequestBody JobBean body) {
        JobBean jobBean = repo.save(body);
        return GenericRestResponse.ok(jobBean.getId());
    }

    @Override
    public Iterable<JobBean> getLike(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repo.deleteById(id);
    }

}
