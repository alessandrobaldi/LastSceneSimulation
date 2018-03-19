package ca.study.scenary.aedes;

import br.ufes.inf.lprm.scene.SceneApplication;
import br.ufes.inf.lprm.scene.base.listeners.SCENESessionListener;
import ca.study.scenary.entities.Scenary;
import ca.study.scenary.entities.House;
import ca.study.scenary.entities.Mosquito;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.Results;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
//import org.kie.api.runtime.rule.FactHandle;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
/**
 * This is a sample class to launch a rule.
 */

public class AedesMain {

	//static final Logger LOG = LoggerFactory.getLogger(AedesMain.class);

    public static final void main(String[] args) throws InterruptedException, ExecutionException{

    	try{
	        System.out.print("Starting Project");
	    	KieServices kieServices = KieServices.Factory.get();
	
	        KieContainer kContainer = kieServices.getKieClasspathContainer();
	        
	        //Results verifyResults = kContainer.verify();
	        //for (Message m : verifyResults.getMessages()) {
	            //LOG.info("{}", m);
	       // }
	
	        /*Par�metros:
	         * 1�) casas existentes no cen�rio
	         * 2�) rela��o de vizinhan�a (quem � vizinho de quem)
	         * 3�) casas que possuem armadilhas
	         * 4�) casas com foco de mosquito
	         * 5�) casas que possuem mosquito
	         * 6�) n�mero de dias que o cen�rio ser� executado. */
	        Scenary ufes = new Scenary("c0,c1,c2,c3,c4,c5,c6,c7,c8,c9",
					   "c0-c1,c1-c2,c2-c3,c3-c4,c4-c5,c5-c6,c6-c7,c7-c8,c8-c9",
					   "",
					   "c0,c1,c2,c3,c4,c5,c6,c7,c8,c9",
					   "c0");
	        
	       //LOG.info("Creating kieBase");
	        System.out.print("Creating kieBase");
	
	        KieBaseConfiguration config = KieServices.Factory.get().newKieBaseConfiguration();
	        config.setOption(EventProcessingOption.STREAM);
	        //config.setOption(ClockTypeOption.get("pseudo"));
	        KieBase kieBase = kContainer.newKieBase(config);
	        //LOG.info("There should be rules: ");
	        /*for ( KiePackage kp : kieBase.getKiePackages() ) {
	            for (Rule rule : kp.getRules()) {
	                LOG.info("kp " + kp + " rule " + rule.getName());
	            }
	        }*/
	
	        //LOG.info("Creating kieSession");
	        System.out.println("Creating kieSession");
	        KieSessionConfiguration config2 = kieServices.newKieSessionConfiguration();
	    	config2.setOption( ClockTypeOption.get("pseudo") );
	        KieSession session = kieBase.newKieSession(config2,null);
	       
	        @SuppressWarnings("unused")
			SceneApplication app = new SceneApplication("Aedes", session);
	
	        session.addEventListener(new SCENESessionListener());
	        
	
	        //final RuleEngineThread ruleEngineThread = new RuleEngineThread(session);
	       // ruleEngineThread.start();
	        //LOG.info("Now running data");
	        System.out.println("Now running data");   
	     
	        //Insere todas as coisas na work memory
	        for(House h : ufes.getScenary()) {
	        	  session.insert(h);
	        	System.out.println(h);
	        	for(Mosquito m : h.getMosquitos()){
		        session.insert(m);
		        	System.out.println(m);
	        	}
	        }
	        
	        //fh=session.insert(ufes);
	        long start = System.currentTimeMillis();
	        SessionPseudoClock clock = session.getSessionClock();
	        int contador = 0;
	        while(contador<70){
	        	session.fireAllRules();
        		contador=contador+1;
             	clock.advanceTime(1,TimeUnit.DAYS);
             	clock = session.getSessionClock();
             	Thread.sleep(1000);
            //	System.out.println(clock.getCurrentTime());
            }
	        System.out.println("acabou");
	        long elapsed = System.currentTimeMillis() - start;
	        System.out.println("Time:"+elapsed);
	        /*
	        ExecutorService executor = Executors.newSingleThreadExecutor();
	        Future<String> future = executor.submit(new Task());
	        
	        //Deixa a thread principal rodando por 30 segundos, após esse tempo, mata ela!
	        try {
	            System.out.println("Started..");
	            System.out.println(future.get(30, TimeUnit.DAYS));
	            System.out.println("Finished!");
	        } catch (TimeoutException e) {
	            future.cancel(true);
	            System.out.println("Terminated!");
	            System.exit(0);
	        }

	        executor.shutdownNow();*/
	        
    	}
    	catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
    	while (!Thread.interrupted()) {
    	}
		return "Ready";
    }
}