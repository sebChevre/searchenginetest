package ch.globaz.searchenginetest;


import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


public class App {

	public static void main (String ...args) {


		System.out.println("ok");

		TransportClient client = null;
		try {
			Settings settings = Settings.builder().put("cluster.name", "docker-cluster").build();

			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));


			//fillEs(client);

			MultiMatchQueryBuilder q = QueryBuilders.multiMatchQuery("Lonie","prenom","nom");// Text

			SearchResponse resp = client.prepareSearch("personne").setQuery(q).get();

			resp.getHits().forEach(personne -> {
				System.out.println(personne);
			});

			System.out.println(resp);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		System.out.println(client);

		client.close();



	}

	private static void fillEs(Client client){


		BulkRequestBuilder bulkRequest = client.prepareBulk();

		PeopleDataGenerator.generateRandomPersonns().stream().forEach(personne -> {
			try {
				bulkRequest.add(client.prepareIndex("personne","fake_personne",personne.id())
				.setSource(jsonBuilder()
							.startObject()
								.field("nom",personne.nom())
								.field("prenom",personne.prenom())
				.endObject()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});


		BulkResponse bulkResponse = bulkRequest.get();



	}


}
