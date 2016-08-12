<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class schedule_model extends CI_Model {

	public function getBus()
	{
		$this->db->select();
		$this->db->from('buses');
		$this->db->where('active',1);

		return $this->db->get()->result_array();
	}


	public function getRoute()
	{
		$this->db->select();
		$this->db->from('routes');
		$this->db->where('active',1);

		return $this->db->get()->result_array();
	}


	public function getSchedule()
	{
		$this->db->select();
		$this->db->from('schedule');
		$this->db->join('buses','schedule.bus_id = buses.bus_id');
		$this->db->join('routes','schedule.route_id = routes.route_id');
		$this->db->where('schedule.active',1);

		return $this->db->get()->result_array();
	}


	public function getDailySchedule()
	{
		$condition = array('daily_schedule.active' => 1,"daily_schedule.date"=>date("Y-m-d"));

		$this->db->select();
		$this->db->from('daily_schedule');
		$this->db->join('schedule', 'schedule.schedule_id = daily_schedule.schedule_id');
		$this->db->join('buses','schedule.bus_id = buses.bus_id');
		$this->db->join('routes','schedule.route_id = routes.route_id');
		$this->db->where($condition);

		$temp = $this->db->get()->result_array();

		$query = $this->db->query("SELECT daily_schedule_id, COUNT( * ) as tickets FROM  `daily_ticket` WHERE active = 1 GROUP BY `daily_schedule_id`");
		$tickets = $query->result_array();

		foreach ($tickets as $key => $value) {
			foreach ($temp as $k => $v) {
				if($v['daily_schedule_id'] === $value['daily_schedule_id']){
					$temp[$k]['seats'] = 40 - $value['tickets']; 					
				} else if(!isset($temp[$k]['seats'])){
					$temp[$k]['seats'] = 40;
				}
			}
		}

		return $temp;
	}




	public function getTickets($id)
	{	
		$this->db->select();									 
		$this->db->from('daily_ticket');						  	
		$this->db->join('tickets', 'daily_ticket.ticket_id = tickets.ticket_id');
		$this->db->join('daily_schedule','daily_schedule.daily_schedule_id = daily_ticket.daily_schedule_id');
		$this->db->where('daily_schedule.daily_schedule_id',$id);

		return $this->db->get()->result_array();
	}

	public function getAdmin(){
		$this->db->select();
		$this->db->from('admins');
		$this->db->where('active',1);

		return $this->db->get()->result_array();
	}

}

/* End of file schedule_model.php */
/* Location: ./application/models/schedule_model.php */