using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class blastController : MonoBehaviour
{
    public float shootSpeed;
    public Transform playerPosition;

    Rigidbody2D shootRB;

    // Start is called before the first frame update
    void Start()
    {
        shootRB = GetComponent<Rigidbody2D>();

        if(playerPosition != null)
        {
            // Calculate direction to the player
            Vector2 direction = (playerPosition.position - transform.position).normalized;

            // Applying force in the direction of the player
            shootRB.velocity = direction * shootSpeed;
        }
    }

    // Update is called once per frame
    void Update()
    {
        if(playerPosition != null)
        {
            Vector2 direction = (playerPosition.position - transform.position).normalized;
            shootRB.velocity = direction * shootSpeed;
        }
    }
}
