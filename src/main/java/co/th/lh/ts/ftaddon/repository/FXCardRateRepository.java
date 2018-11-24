package co.th.lh.ts.ftaddon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.th.lh.ts.ftaddon.domain.VFXCardRate;

@Repository
public interface FXCardRateRepository extends JpaRepository<VFXCardRate,String>{
      public List<VFXCardRate>  findAllByOrderByCurrencySeqAsc(); 
      
      @Query( value = "SELECT  COUNT(0) FROM lh_batch_step_execution st WHERE  st.step_name = ?1 "+
                      "AND   st.exit_code = 'COMPLETED'   AND   trunc(st.last_updated) = TO_DATE(?2,'dd/MM/yyyy')", 
    		  nativeQuery = true)
      public int getCountStepJob(String stepName, String dateStr);
}
