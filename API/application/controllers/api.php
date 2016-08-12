<?php defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH.'/libraries/REST_Controller.php';


class api extends REST_Controller{
	function __construct(){
		parent::__construct();
	}

	/*
		shows all the buses
		use : http://localhost/API/index.php/api/bus
			
	*/
		function Bus_get() {
		//$id = $this->get('id');
		//$id = $this->uri->segment(3);
		//$date = date("Y-m-d");


			$this->load->model('schedule_model');
			$bus = $this->schedule_model->getBus();

			if(isset($bus)){
				json_encode($bus);
				$this->response(array('status'=> 'sucess','message'=>$bus));	
			} else {
				$this->response(array('status'=> 'failure', 'message'=> "Not found"),REST_Controller::HTTP_NOT_FOUND);
			}
		}




	/*
		shows all the routes
		use : http://localhost/API/index.php/api/route
			
	*/
		function Route_get() {
			$this->load->model('schedule_model');
			$route = $this->schedule_model->getRoute();

			if(isset($route)){
				$this->response(array('status'=> 'sucess','message'=>$route));	
			} else {
				$this->response(array('status'=> 'failure', 'message'=> "Not found"),REST_Controller::HTTP_NOT_FOUND);
			}
		}


	/*
		Sends all the schedule
		http://localhost/API/index.php/api/schedule
	*/

		function Schedule_get() {
			$this->load->model('schedule_model');
			$schedule = $this->schedule_model->getSchedule();

			if(isset($schedule)){
				$this->response(array('status'=> 'sucess','message'=>$schedule));	
			} else {
				$this->response(array('status'=> 'failure', 'message'=> "Not found"),REST_Controller::HTTP_NOT_FOUND);
			}
		}



	/*
		Sends today's schedule 
		use : http://localhost/API/index.php/api/DailySchedule 
	*/
		function DailySchedule_get() {
			$this->load->model('schedule_model');
			$schedule = $this->schedule_model->getDailySchedule();

			if(isset($schedule)){
				$this->response(array('status'=> 'sucess','message'=>$schedule));	
			} else {
				$this->response(array('status'=> 'failure', 'message'=> "Not found"),REST_Controller::HTTP_NOT_FOUND);
			}
		}


		/*
			shows which tickets are booked or confirmed
			use : http://localhost/API/index.php/api/ticket/1
			
			takes a parameter daily schedule id.			
		*/

			function Tickets_get() {
				$id = $this->uri->segment(3);

				$this->load->model('schedule_model');
				$ticket = $this->schedule_model->getTickets($id);

				if(isset($ticket)){
					$this->response(array('status'=> 'sucess','message'=>$ticket));	
				} else {
					$this->response(array('status'=> 'failure', 'message'=> "Not found"),REST_Controller::HTTP_NOT_FOUND);
				}
			}


		/*
			shows all the admins
			use : http://localhost/API/index.php/api/admin
			
		*/


			function Admin_get() {
				$this->load->model('schedule_model');
				$admin = $this->schedule_model->getAdmin();

				if(isset($admin)){
					$this->response(array('status'=> 'sucess','message'=>$admin));	
				} else {
					$this->response(array('status'=> 'failure', 'message'=> "Not found"),REST_Controller::HTTP_NOT_FOUND);
				}
			}





			public function EntryRoute_put()
			{
				$this->load->library('form_validation');
				$this->load->model('schedule_model');

				$this->form_validation->set_rules($this->put('name'), 'Username', 'required');
		//$this->form_validation->set_rules('end_date', 'End date', 'required');

		/*if ($this->form_validation->run() == FALSE)
		{
			$this->response(array('status'=> 'failure', 'message'=> "required fields are empty"),REST_Controller::HTTP_NOT_FOUND);
		}
		else
		{
			$this->response(array('status'=> 'sucess', 'message'=> "WOW"),REST_Controller::HTTP_NOT_FOUND);			
			//$this->Programmodel->add($data , $this->input->post('subjects[]'));
		}*/
		//var_dump($this->put('siam'));
		var_dump($this->put());

	}

}
