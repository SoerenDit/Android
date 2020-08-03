using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace SkejbyDiceDotnet.Webapi.Controllers
{
	[ApiController, Route("[controller]")] 
	public class PlayerController : ControllerBase
	{
		private static List<Player> players { get; set; } = new List<Player>();

		[HttpGet("all")]
		public List<Player> GetPlayers()
		{
			return players;
		}

		[HttpPost("simple")]
		public void AddPlayer(string name)
		{
			var p = new Player();
			p.Name = name;
			players.Add(p);
		}

		[HttpPost("full")]
		public void AddFullPlayer([FromBody] Player p)
		{
					players.Add(p);
		}

	}

}
