using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class playerController : MonoBehaviour
{
    // Player movement variables
    public float maxSpeed;

    // Player jumping variables
    bool grounded = false;
    bool isJumping = false;
    float groundCheckRadius = 0.2f;
    public LayerMask groundLayer;
    public Transform groundCheck;
    public float jumpHeight;

    // For attacking...
    public Transform playerSwordSwing; // Origin of the slash attack sprite
    public GameObject slashBox; // Variable to set the slash game object
    float attackSpeed = 1f; // Speed of attack
    float nextAttack = 0f; // Time of last attack

    Rigidbody2D pcRB; // Variable to refer to RigidBody2d
    Animator pcAnim; // Variable to refer to the Animator
    bool facingRight; // Boolean to check if the pc is facing right

    // Start is called before the first frame update
    void Start()
    {
        pcRB = GetComponent<Rigidbody2D>(); // Get the Rigid Body 2D component form the Unity Engine
        pcAnim = GetComponent<Animator>(); // Get the Animator component from the Unity Engine

        facingRight = true; // Set facing right to true as the pc sprite starts of by facing right
    }

    void Update()
    {
        if(grounded && Input.GetAxis("Jump") > 0)
        {
            isJumping = true;
            grounded = false;
            pcAnim.SetBool("isGrounded", grounded);
            pcRB.AddForce(new Vector2(0, jumpHeight));
        }

        // Player using sword...
        if(Input.GetAxisRaw("Fire1") > 0 && grounded && !isJumping)
        {
            swordSlash(); // Call swordSlash
        }
    }

    // Fixed Update is called after a specific amount of time all the time
    void FixedUpdate()
    {
        // Check if pc is grounded. If not then pc is falling
        grounded = Physics2D.OverlapCircle(groundCheck.position, groundCheckRadius, groundLayer);
        pcAnim.SetBool("isGrounded", grounded);

        pcAnim.SetFloat("verticalSpeed", pcRB.velocity.y);

        if(grounded)
        {
            isJumping = false;
        }

        float move = Input.GetAxis("Horizontal"); // Get x axis
        pcAnim.SetFloat("speed", Mathf.Abs(move)); // Get the absolute value when it is not 0

        pcRB.velocity = new Vector2(move*maxSpeed, pcRB.velocity.y);

        if(move > 0 && !facingRight)
        {
            flipPC();
        }
        else if(move < 0 && facingRight)
        {
            flipPC();
        }
    }
    
    // flip will be used to reverse the character sprite...
    void flipPC()
    {
        facingRight = !facingRight;

        Vector3 flipScale = transform.localScale; // Set varaible to allow scale to flip
        flipScale.x *= -1; // Turn the x scale into its opposite
        transform.localScale = flipScale;
    }

    void swordSlash()
    {
        if(Time.time > nextAttack)
        {
            // Setting up the atack speed and attack animation
            nextAttack = Time.time + attackSpeed;
            pcAnim.SetTrigger("playerAttack");

            // Instantiate the slash attack directly on top of attackOrigin
            Instantiate(slashBox, playerSwordSwing.position, Quaternion.Euler(new Vector3(0, 0, 0)));   
        }

    }
}
