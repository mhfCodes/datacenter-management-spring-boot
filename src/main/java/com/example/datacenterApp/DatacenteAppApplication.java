package com.example.datacenterApp;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.datacenterApp.models.ProviderModel;
import com.example.datacenterApp.models.RackModel;
import com.example.datacenterApp.models.RoleModel;
import com.example.datacenterApp.models.ServerModel;
import com.example.datacenterApp.models.TreeModel;
import com.example.datacenterApp.models.UserModel;
import com.example.datacenterApp.repository.RackRepo;
import com.example.datacenterApp.repository.RoleRepo;
import com.example.datacenterApp.repository.ServerRepo;
import com.example.datacenterApp.repository.TreeRepo;
import com.example.datacenterApp.repository.UserRepo;

@SpringBootApplication
public class DatacenteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatacenteAppApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(RackRepo rackRepo, ServerRepo serverRepo,
			 RoleRepo roleRepo, UserRepo userRepo, TreeRepo treeRepo) {
		
		return args -> {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			RoleModel admin = new RoleModel("ROLE_ADMIN");
			RoleModel adminTrainee = new RoleModel("ROLE_ADMIN_TRAINEE");
			
			UserModel john = new UserModel("john", passwordEncoder.encode("1234"), admin);
			UserModel alex = new UserModel("alex", passwordEncoder.encode("1234"), adminTrainee);
			
			userRepo.saveAll(List.of(john, alex));
			roleRepo.saveAll(List.of(admin, adminTrainee));
			
			RackModel rack1 = new RackModel(42);
			RackModel rack2 = new RackModel(48);
						
			ServerModel server1 = new ServerModel("IBM", "Linux", "Intel", 64, 5, 8, ProviderModel.BLUEHOST);
			ServerModel server2 = new ServerModel("Lenovo", "Windows", "AMD", 32, 10, 15, ProviderModel.DREAMHOST);
			ServerModel server3 = new ServerModel("Dell", "Windows Server 2008","AMD", 64, 25, 36, ProviderModel.HOSTGATOR);
			ServerModel server4 = new ServerModel("IBM", "Linux", "Intel", 32, 2, 4, ProviderModel.BLUEHOST);
			ServerModel server5 = new ServerModel("Dell", "CentOS", "AMD", 32, 18, 23, ProviderModel.DREAMHOST);
			ServerModel server6 = new ServerModel("Lenovo", "Windwos", "Intel", 64, 24, 30, ProviderModel.DREAMHOST);
			serverRepo.saveAll(List.of(server1, server2, server3, server4, server5, server6));
			rackRepo.saveAll(List.of(rack1, rack2));
			
			server1.setRack(rack1);
			server2.setRack(rack1);
			server3.setRack(rack1);
			server4.setRack(rack2);
			server5.setRack(rack2);
			server6.setRack(rack2);
			
			rack1.setServers(List.of(server1, server2, server3));
			rack2.setServers(List.of(server4, server5, server6));

			serverRepo.saveAll(List.of(server1, server2, server3, server4, server5, server6));
			rackRepo.saveAll(List.of(rack1, rack2));
			
			TreeModel root = new TreeModel(null);
			treeRepo.save(root);
			
			TreeModel rack1Node = new TreeModel(root.getId(), rack1);
			TreeModel rack2Node = new TreeModel(root.getId(), rack2);
			treeRepo.saveAll(List.of(rack1Node, rack2Node));
			
			TreeModel server1Node = new TreeModel(rack1Node.getId(), server1);
			TreeModel server2Node = new TreeModel(rack1Node.getId(), server2);
			TreeModel server3Node = new TreeModel(rack1Node.getId(), server3);
			treeRepo.saveAll(List.of(server1Node, server2Node, server3Node));
			
			TreeModel server4Node = new TreeModel(rack2Node.getId(), server4);
			TreeModel server5Node = new TreeModel(rack2Node.getId(), server5);
			TreeModel server6Node = new TreeModel(rack2Node.getId(), server6);
			treeRepo.saveAll(List.of(server4Node, server5Node, server6Node));

			treeRepo.findAll().forEach(node -> {
				String path = (node.getParentId() == null) ? null : 
					generatePath(treeRepo.findById(node.getParentId()).orElseThrow(), treeRepo);
				node.setPath(path);
				treeRepo.save(node);
			});
			
		};
	}
	
	public String generatePath(TreeModel node, TreeRepo treeRepo){
		
		if(node.getParentId() == null) {
			return "1";
		} else {
			return generatePath(treeRepo.findById(node.getParentId())
					.orElseThrow(() -> new IllegalStateException("Id Not Found")), treeRepo) + "/" + node.getId();
		}
		
	}

}
